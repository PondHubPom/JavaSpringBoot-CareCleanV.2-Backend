package com.Hoime.CareClean.services;

//import com.Hoime.CareClean.enums.CategoryLockerId;
import com.Hoime.CareClean.enums.ExceptionTitleEnum;
import com.Hoime.CareClean.model.domain.PersonalDataDomain;

import com.Hoime.CareClean.model.domain.*;
import com.Hoime.CareClean.model.entity.MemberEntity;
import com.Hoime.CareClean.model.entity.BookingEntity;
import com.Hoime.CareClean.model.entity.MemberListEntity;
import com.Hoime.CareClean.model.entity.HistoryBookingEntity;
import com.Hoime.CareClean.model.wrapper.*;
import com.Hoime.CareClean.repository.BookingRepository;
import com.Hoime.CareClean.repository.MemberListRepository;
import com.Hoime.CareClean.repository.MemberRepository;
import com.Hoime.CareClean.repository.HistoryBookingRepository;
import com.Hoime.CareClean.security.EmailValidator;
import com.Hoime.CareClean.security.Security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class MembersService {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberListRepository memberListRepository;

    @Autowired
    private HistoryBookingRepository HistoryBookingRepository;
    @Autowired
    private BookingRepository bookingRepository;

    //    @Autowired
//    private MemberRepository memberRepo;
//    @Autowired
//    private StatisticsMenuRepository staticRepo;
//    @Autowired
//    private HistoryTransferRepository historyTransferRepo;
    @Autowired
    private Security security;

    @Autowired
    private UtilService utilService;


    //-------- Sign In --------------

    public MemberDomain login(MemberWrapper w) {

        MemberDomain memberDomain = new MemberDomain();

        if (!EmailValidator.isValidEmail(w.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Bad request");
        }

        MemberEntity entity = memberRepository.findByEmail(w.getEmail());
        utilService.checkActive(entity.isActive());
    //-------- เปรียบเทียบ password ที่ hash ไว้ใน database กับ password ที่ใส่มาในหน้า login --------------
        if (security.comparePasswords(w.getPassword(), entity.getPassword())) {
            //-------- get ค่า id แล้วสร้าง token ด้วยฟังก์ชั่นที่สร้างเอาไว้ --------------
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", entity.getId());
            String token = security.generateToken(claims);

            memberDomain.setAccessToken(token);
            memberDomain.setAccountId(entity.getId());
            memberDomain.setAdmin(entity.isAdmin());
            memberDomain.setActive(entity.isActive());

            return memberDomain;
        } else {
            //-------- Exception Handling --------------
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,
                    "Invalid login credentials.");
        }
    }

    //-------- Sign Up --------------

    public MemberDomain register(MemberWrapper w) {

        String name = w.getName();
        String tel = w.getTel();
        String birthday = w.getBirthday();
        String email = w.getEmail();
        String sex = w.getSex();
        String address = w.getAddress();
        String password = w.getPassword();

        if (name.isEmpty()
                || tel.isEmpty()
                || address.isEmpty()
                || tel.length() < 10
                || tel.length() > 10
                || birthday.isEmpty()
                || name.length() < 6
                || sex.isEmpty()
                || password.length() < 8
                || !EmailValidator.isValidEmail(email)) {

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Please provide valid input");
        }

        if (memberRepository.findByEmail(email) != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "This Email is already taken");
        }

        if (memberRepository.findByTel(tel) != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "This Telephone Number is already exist");
        }


        MemberEntity entity = w.clone().toEntity();
        entity.setPassword(security.hashPassword(password));

        entity.setActive(true);
        entity.setAdmin(false);
        memberRepository.save(entity);

//         Login after register
        return this.login(w);
    }

    public PersonalDataDomain getPersonalData(String id) {
        PersonalDataDomain domain = new PersonalDataDomain();
        Optional<MemberEntity> optional = memberRepository.findById(id);
        if (optional.isPresent()) {
            MemberEntity entity = optional.get();
            utilService.checkActive(entity.isActive());
            domain.setEmail(entity.getEmail());
            domain.setName(entity.getName());
            domain.setTel(entity.getTel());
            domain.setBirthday(entity.getBirthday().toString());
            domain.setAge(utilService.calculateAge(entity.getBirthday()));
            domain.setAddress(entity.getAddress());
            domain.setSex(entity.getSex());
            return domain;
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Member not found.");
        }
    }

    public UtilDomain editPersonalData(String id, EditPersonalWrapper wrapper) {
        Optional<MemberEntity> optional = memberRepository.findById(id) ;
        if (optional.isPresent()) {
            MemberEntity entity = optional.get();
            utilService.checkActive(entity.isActive());
            entity.editPersonal(wrapper);
            String name = wrapper.getName();
            String tel = wrapper.getTel();
            String birthday = wrapper.getBirthday().toString();
            String sex = wrapper.getSex();
            String address = wrapper.getAddress();
            if (name.isEmpty()
                    || tel.isEmpty()
                    || address.isEmpty()
                    || tel.length() < 10
                    || birthday.isEmpty()
                    || name.length() < 6
                    || sex.isEmpty()) {

                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Please provide valid input");
            }

            memberRepository.save(entity);

            return new UtilDomain(HttpStatus.OK.value(), "Success");
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Member Not Found");
        }
    }

    public List<HistoryBookingDomain> getHistoryBookingDomain(String id) {
        List<HistoryBookingEntity> entity = HistoryBookingRepository.findByAccountId(id);
        List<HistoryBookingDomain> domain = new ArrayList<>();

        for (HistoryBookingEntity e : entity) {
            domain.add(new HistoryBookingDomain(e));
        }

        return domain;
    }
    public UtilDomain toAdmin(String id) {
        Optional<MemberEntity> optional = memberRepository.findById(id);
        if (optional.isPresent()) {
            MemberEntity entity = optional.get();
            utilService.checkActive(entity.isActive());
            if (!entity.isActive()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User Not activity");
            if (!entity.isAdmin()) {
                entity.setAdmin(true);
                memberRepository.save(entity);
                return new UtilDomain(HttpStatus.OK.value(), "Success");
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You is already Admin");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Member Not Found");
        }
    }

    public List<MemberListDomain> getMemberListDomain(String id) {
        Optional<MemberEntity> optional = memberRepository.findById(id);
        Iterable<MemberEntity> entity = memberRepository.findAll();
        List<MemberListDomain> domain = new ArrayList<>();
        if (optional.isPresent()) {

            for (MemberEntity e : entity) {
                domain.add(new MemberListDomain(e));
            }

        }
        return domain;
    }

    public LocationDomain getLocationData(String id) {
        LocationDomain domain = new LocationDomain();
        Optional<MemberEntity> optional = memberRepository.findById(id);
        if (optional.isPresent()) {
            MemberEntity entity = optional.get();
            utilService.checkActive(entity.isActive());
            domain.setAddress(entity.getAddress());
            return domain;
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Member not found.");
        }
    }

    public List<BookingListDomain> getBookingListDomain(String id) {
        Optional<MemberEntity> optional = memberRepository.findById(id);
        Iterable<BookingEntity> entity = bookingRepository.findAll();
        List<BookingListDomain> domain = new ArrayList<>();
        if (optional.isPresent()) {

            for (BookingEntity e : entity) {
                domain.add(new BookingListDomain(e));
            }

        }
        return domain;
    }

    public UtilDomain resetPassword(String id,ResetPasswordWrapper resetPasswordWrapper) {
        Optional<MemberEntity> optional = memberRepository.findById(id);
        String oldPassword = resetPasswordWrapper.getOldPassword();
        String newPassword = resetPasswordWrapper.getNewPassword();
        if (oldPassword.isEmpty()
                || newPassword.isEmpty()
                || newPassword.length() < 8
                || oldPassword.length() < 8) {

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Please provide valid input");
        }

        if (optional.isPresent()) {
            MemberEntity entity = optional.get();
            utilService.checkActive(entity.isActive());

            if (security.comparePasswords(oldPassword, entity.getPassword())) {
                entity.setPassword(security.hashPassword(newPassword));
                memberRepository.save(entity);

                return new UtilDomain(HttpStatus.OK.value(), "Success");
            } else {
                throw new ResponseStatusException(HttpStatus.CONFLICT,
                        "Incorrect old password.");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Member not found.");
        }
    }

    public UtilDomain removeAccount(String id,IdWrapper w) {

        Optional<MemberEntity> optional = memberRepository.findById(w.getAccountId());
        System.out.println(w.getAccountId());
        if (optional.isPresent()) {
            List<HistoryBookingEntity> enti = HistoryBookingRepository.findByAccountId(w.getAccountId());
            List<BookingEntity> en = bookingRepository.findByAccountId(w.getAccountId());

            for (HistoryBookingEntity e : enti) {
                e.setStatee("Cancel");
            }

            for(BookingEntity ent : en){
                ent.setStatee("Cancel");
            }

            HistoryBookingRepository.saveAll(enti);
            bookingRepository.saveAll(en);

            MemberEntity entity = optional.get();
            entity.setActive(false);
            memberRepository.save(entity);

            return new UtilDomain(HttpStatus.OK.value(), "Success");
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ExceptionTitleEnum.MEMBER_ERROR.getTitle());
        }
    }

    public UtilDomain retrieveAccount(String id,IdWrapper w) {

        Optional<MemberEntity> optional = memberRepository.findById(w.getAccountId());
        System.out.println(w.getAccountId());
        if (optional.isPresent()) {
            MemberEntity entity = optional.get();
            entity.setActive(true);
            memberRepository.save(entity);

            return new UtilDomain(HttpStatus.OK.value(), "Success");
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ExceptionTitleEnum.MEMBER_ERROR.getTitle());
        }
    }

}