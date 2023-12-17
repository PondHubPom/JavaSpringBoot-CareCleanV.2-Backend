package com.Hoime.CareClean.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
//import javax.persistence.Id;

import java.time.LocalDate;

@Document(indexName = "service")
@Getter
@Setter
public class BookingEntity {
    @Id
    @ReadOnlyProperty
    private String bookingId;

//    @Field(type = FieldType.Date,
//            format = DateFormat.date_optional_time,
//            name = "bookingDate")
//    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC")
//    private LocalDate bookingDate;

    @Field(type = FieldType.Text, name = "bookingDate")
    private String bookingDate;

    @Field(type = FieldType.Text, name = "timeslot")
    private String timeslot;

//    @Field(type = FieldType.Text, name = "desc")
//    private String desc;

    @Field(type = FieldType.Text,name = "statee")
    private String statee;

    @Field(type = FieldType.Text, name = "accountId")
    private String accountId;

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

}
