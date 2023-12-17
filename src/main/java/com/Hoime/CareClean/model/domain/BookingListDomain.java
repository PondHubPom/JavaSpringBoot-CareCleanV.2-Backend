package com.Hoime.CareClean.model.domain;

import com.Hoime.CareClean.model.entity.BookingEntity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;


@Getter
@Setter
public class BookingListDomain {
    private String bookingId;
    private String bookingDate;
    private String timeslot;
    private String statee;
    private String accountId;
    private String select;
    private String amount;
    private String payment;
    private String select_payment;
    private String price;
    private String title;
    private String path;
    private String address;

    public BookingListDomain(){
    }
    public BookingListDomain(BookingEntity e){
        this.bookingId = e.getBookingId();
        this.bookingDate = e.getBookingDate().toString();
        this.timeslot = e.getTimeslot();
        this.statee = e.getStatee();
        this.accountId = e.getAccountId();
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
