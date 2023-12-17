package com.Hoime.CareClean.model.wrapper;

import com.Hoime.CareClean.model.entity.BookingEntity;
import com.Hoime.CareClean.model.entity.MemberEntity;
import com.Hoime.CareClean.services.UtilService;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingWrapper implements Cloneable{
    private String bookingDate;
    private String timeslot;
    private String select;
    private String amount;
    private String payment;
    private String select_payment;
    private String price;
    private String path;
    private String title;

    public BookingWrapper(){
    }

    public BookingWrapper(BookingEntity e) {
        this.bookingDate = e.getBookingDate().toString();
        this.timeslot = e.getTimeslot();
        this.select = e.getSelect();
        this.amount = e.getAmount();
        this.payment = e.getPayment();
        this.select_payment = e.getSelect_payment();
        this.price = e.getPrice();
        this.path = e.getPath();
        this.title = e.getTitle();
    }

    public BookingEntity toEntity() {
        BookingEntity e = new BookingEntity();
        e.setBookingDate(this.bookingDate);
//        e.setBookingDate(new UtilService().coverStrToLocaltime(this.bookingDate));
        e.setTimeslot(this.timeslot);
        e.setSelect(this.select);
        e.setAmount(this.amount);
        e.setPayment(this.payment);
        e.setSelect_payment(this.select_payment);
        e.setPrice(this.price);
        e.setPath(this.path);
        e.setTitle(this.title);
        return e;
    }

    @Override
    public BookingWrapper clone() {
        try {
            return (BookingWrapper) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}