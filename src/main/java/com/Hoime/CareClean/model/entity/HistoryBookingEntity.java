package com.Hoime.CareClean.model.entity;

//import com.Hoime.CareClean.services.HistoryBookingService;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDate;
@Getter
@Setter
@Document(indexName = "history-booking")
public class HistoryBookingEntity {
    @Id
    @ReadOnlyProperty
    private String id;

    @Field(type = FieldType.Text, name = "accountId")
    private String accountId;

    @Field(type = FieldType.Text,name = "bookingId")
    private String bookingId;

//    @Field(type = FieldType.Text, name = "desc")
//    private String desc;

    @Field(type = FieldType.Text, name = "statee")
    private String statee;

    @Field(type = FieldType.Text, name = "date")
    private String date;

//    @Field(type = FieldType.Date,
//            format = DateFormat.date_optional_time,
//            name = "bookingDate")
//    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC")
//    private LocalDate bookingDate;

    @Field(type = FieldType.Text, name = "bookingDate")
    private String bookingDate;

    @Field(type = FieldType.Text, name = "timeslot")
    private String timeslot;

    @Field(type = FieldType.Text, name = "select")
    private String select;

    @Field(type = FieldType.Text, name = "amount")
    private String amount;

    @Field(type = FieldType.Text, name = "payment")
    private String payment;

    @Field(type = FieldType.Text, name = "select_payment")
    private String select_payment;

    @Field(type = FieldType.Text, name = "price")
    private String price;

    @Field(type = FieldType.Text, name = "title")
    private String title;

    @Field(type = FieldType.Text, name = "address")
    private String address;

    @Field(type = FieldType.Text, name = "path")
    private String path;

//    @Field(type = FieldType.Text, name = "bookingDate")
//    private String bookingDate;
public HistoryBookingEntity() {

}

    public HistoryBookingEntity(String accountId,String statee,String timeslot ,String select,String amount,String payment,String select_payment,String price,String path,String title,String address) {
        this.accountId = accountId;
        this.statee = statee;
        this.timeslot = timeslot;
        this.select = select;
        this.amount = amount;
        this.payment = payment;
        this.select_payment = select_payment;
        this.price = price;
        this.path = path;
        this.title = title;
        this.address = address;
    }

}

