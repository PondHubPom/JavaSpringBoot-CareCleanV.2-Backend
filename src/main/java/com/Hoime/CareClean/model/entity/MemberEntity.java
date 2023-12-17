package com.Hoime.CareClean.model.entity;

import com.Hoime.CareClean.model.wrapper.EditPersonalWrapper;

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

@Document(indexName = "member")
@Getter
@Setter
public class MemberEntity {
    @Id
    @ReadOnlyProperty

    private String id;

    @Field(type = FieldType.Text, name = "name")
    private String name;

    @Field(type = FieldType.Text, name = "tel")
    private String tel;

    @Field(type = FieldType.Text, name = "email")
    private String email;

    @Field(type = FieldType.Text, name = "password")
    private String password;

    @Field(type = FieldType.Text, name = "address")
    private String address;

    @Field(type = FieldType.Date,
            format = DateFormat.date_optional_time,
            name = "birthday")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC")
    private LocalDate birthday;

    @Field(type = FieldType.Boolean, name = "admin")
    private boolean admin;

    @Field(type = FieldType.Text, name = "sex")
    private String sex;

    @Field(type = FieldType.Boolean,name = "isActive")
    private boolean isActive;

    public void editPersonal(EditPersonalWrapper wrapper) {
        this.name = wrapper.getName();
        this.birthday = wrapper.getBirthday();
        this.sex = wrapper.getSex();
        this.address = wrapper.getAddress();
        this.tel = wrapper.getTel();
    }

}
