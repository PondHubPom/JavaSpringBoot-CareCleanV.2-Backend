package com.Hoime.CareClean.model.domain;

import com.Hoime.CareClean.model.entity.HistoryBookingEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;


@Getter
@Setter
public class HistoryBookingDomain {

    private String accountId;
    private String bookingId;
    private String statee;
    private String date;
//    private String desc;
    private String bookingDate;
    private String select;
    private String amount;
    private String payment;
    private String select_payment;
    private String price;
    private String title;
    private String address;
    private String path;


    public HistoryBookingDomain() {
    }

    public HistoryBookingDomain(HistoryBookingEntity e) {
        this.accountId = e.getAccountId();
        this.bookingId = e.getBookingId();
        this.statee = e.getStatee();
        this.date = e.getDate();
//        this.desc = e.getDesc();
        this.bookingDate = e.getBookingDate().toString();
        this.select = e.getSelect();
        this.amount = e.getAmount();
        this.payment = e.getPayment();
        this.select_payment = e.getSelect_payment();
        this.price = e.getPrice();
        this.title = e.getTitle();
        this.address = e.getAddress();
        this.path = e.getPath();
    }
}
