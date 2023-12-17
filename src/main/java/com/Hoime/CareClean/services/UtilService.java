package com.Hoime.CareClean.services;

//import com.Homie.Home_NotPro.enums.CategoryLockerId;
import com.Hoime.CareClean.model.entity.HistoryBookingEntity;
//import com.Homie.Home_NotPro.model.entity.LockerIdEntity;

import com.Hoime.CareClean.model.entity.MemberListEntity;
import com.Hoime.CareClean.security.Security;
import com.Hoime.CareClean.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.Hoime.CareClean.model.entity.MemberEntity;

import java.lang.reflect.Member;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.List;

@Service
public class UtilService {

    @Autowired
    private Security security;

//
    @Autowired
    private HistoryBookingRepository historyBookingRepository;
    @Autowired
    private MemberListRepository memberListRepository;
    @Autowired
    private MemberRepository memberRepository;
//    @Autowired
//    private StoreRepository storeRepository;
//    @Autowired
//    private StatisticsMenuRepository statisticsMenuRepository;
//
//    @Autowired
//    private LockerIdRepository idLockerRepo;

    public void checkActive(boolean active) {
        if (!active) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"This Username is already taken");
    }

    public  LocalDate coverStrToLocaltime(String time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(time, formatter);
    }

    public void bookingSaveHistory(List<HistoryBookingEntity> history) {
        historyBookingRepository.saveAll(history);
    }

    public void memberSaveList(List<MemberListEntity> list) {
        memberListRepository.saveAll(list);
    }

    public int calculateAge(LocalDate birthday) {
        return Period.between(birthday, LocalDate.now()).getYears();
    }

//    public void saveLocker(String category,String idRecord) {
//        LockerIdEntity entity = new LockerIdEntity(idRecord);
//        entity.setIdLocker(security.buildIdLocker(category));
//        idLockerRepo.save(entity);
//    }

//    public String getId(String id) {
//        MemberEntity entity = memberRepository.findById(id);
//        if (entity != null) {
//            return entity.getId();
//        } else {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id Not Found");
//        }
//    }
//
    public String getId(String id) {
        Optional<MemberEntity> optional = memberRepository.findById(id);
        MemberEntity entity = new MemberEntity();
        if (optional.isPresent()) {
            return entity.getId();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id Not Found");
        }
    }
}