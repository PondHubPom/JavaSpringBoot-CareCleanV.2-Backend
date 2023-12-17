package com.Hoime.CareClean.services;

import com.Hoime.CareClean.model.domain.*;
import com.Hoime.CareClean.model.entity.HistoryBookingEntity;
import com.Hoime.CareClean.model.entity.BookingEntity;
import com.Hoime.CareClean.model.entity.MemberEntity;
import com.Hoime.CareClean.model.wrapper.*;
import com.Hoime.CareClean.repository.BookingRepository;
import com.Hoime.CareClean.repository.HistoryBookingRepository;
import com.Hoime.CareClean.repository.MemberRepository;
import com.Hoime.CareClean.security.Security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private HistoryBookingRepository historyBookingRepository;

    @Autowired
    private Security security;

    @Autowired
    private UtilService utilService;

    //-------- Booking --------------

    public BookingDomain booking(BookingWrapper w, String id) {

        BookingDomain bookingDomain = new BookingDomain();
        Optional<MemberEntity> optional = memberRepository.findById(id);
        HistoryBookingEntity historyService = new HistoryBookingEntity();

        if (optional.isPresent()) {
            MemberEntity e = optional.get();
            String bookingId = UUID.randomUUID().toString();
            String bookingDate = w.getBookingDate();
            String timeslot = w.getTimeslot();
            String select = w.getSelect();
            String amount = w.getAmount();
            String payment = w.getPayment();
            String select_payment = w.getSelect_payment();
            String price = w.getPrice();
            String title = w.getTitle();
            String path = w.getPath();

            bookingDomain.setStatee("Pending");
            bookingDomain.setAccountId(id);
            bookingDomain.setBookingId(bookingId);

            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
            String formattedDate = now.format(formatter);

            historyService.setAccountId(id);
            historyService.setBookingId(bookingId);
            historyService.setTimeslot(timeslot);
            historyService.setDate(formattedDate);
            historyService.setBookingDate(bookingDate);
            historyService.setStatee("Pending");
            historyService.setSelect(select);
            historyService.setAmount(amount);
            historyService.setPayment(payment);
            historyService.setSelect_payment(select_payment);
            historyService.setPrice(price);
            historyService.setTitle(title);
            historyService.setAddress(e.getAddress());
            historyService.setPath(path);

            utilService.bookingSaveHistory(Arrays.asList(historyService));

            if (bookingDate.isEmpty()
                    || timeslot.isEmpty()
                    || select.isEmpty()
                    || amount.isEmpty()
                    || payment.isEmpty()
                    || select_payment.isEmpty()
                    || title.isEmpty()
                    || path.isEmpty()
                    || price.isEmpty()) {

                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Please provide valid input");
            }

            BookingEntity entity = w.clone().toEntity();
            entity.setStatee("Pending");
            entity.setAccountId(id);
            entity.setBookingId(bookingId);
            entity.setSelect(select);
            entity.setAmount(amount);
            entity.setPayment(payment);
            entity.setSelect_payment(select_payment);
            entity.setPrice(price);
            entity.setAddress(e.getAddress());


            bookingRepository.save(entity);
            return bookingDomain;
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Member not found.");
        }
    }

    public BookingSuccessDomain bookingSuccess(String id, BookingSuccessWrapper w) {
        Optional<BookingEntity> optional = bookingRepository.findById(w.getBookingId());

        if (optional.isPresent()) {
            BookingEntity entity = optional.get();
            entity.setStatee("Success");
            bookingRepository.save(entity);

            Optional<MemberEntity> my = memberRepository.findById(w.getAccountId());
            HistoryBookingEntity history = historyBookingRepository.findByBookingId(w.getBookingId());

            if (my.isPresent() && history != null) {
                MemberEntity memberEntity = my.get();

                if (Objects.equals(history.getAccountId(), memberEntity.getId())) {
                    history.setStatee("Success");
                    historyBookingRepository.save(history);
                } else {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Service Not Found");
                }
            }
            return new BookingSuccessDomain("Success","Success");
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Service Not Found");
        }
    }

    public BookingSuccessDomain bookingCancel(String id, BookingSuccessWrapper w) {
        Optional<BookingEntity> optional = bookingRepository.findById(w.getBookingId());

        if (optional.isPresent()) {
            BookingEntity entity = optional.get();
            entity.setStatee("Cancel");
            bookingRepository.save(entity);

            Optional<MemberEntity> my = memberRepository.findById(w.getAccountId());
            HistoryBookingEntity history = historyBookingRepository.findByBookingId(w.getBookingId());

            if (my.isPresent() && history != null) {
                MemberEntity memberEntity = my.get();

                if (Objects.equals(history.getAccountId(), memberEntity.getId())) {
                    history.setStatee("Cancel");
                    historyBookingRepository.save(history);
                } else {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Service Not Found");
                }
            }
            return new BookingSuccessDomain("Cancel","Cancel");
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Service Not Found");
        }
    }
}
