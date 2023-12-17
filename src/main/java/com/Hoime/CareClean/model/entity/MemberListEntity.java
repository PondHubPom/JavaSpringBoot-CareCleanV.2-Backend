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

import java.time.LocalDate;

@Document(indexName = "member_list")
@Getter
@Setter
public class MemberListEntity {
    @Id
    @ReadOnlyProperty

    private String id;

    @Field(type = FieldType.Text, name = "accountId")
    private String accountId;

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

    @Field(type = FieldType.Boolean, name = "birthday")
    private String birthday;

    @Field(type = FieldType.Boolean, name = "admin")
    private boolean admin;

    @Field(type = FieldType.Text, name = "sex")
    private String sex;

    @Field(type = FieldType.Boolean,name = "isActive")
    private boolean isActive;

    public MemberListEntity(){
    }

    public MemberListEntity(String accountId,String email, String name, String tel, String address, String birthday, String sex){
        this.accountId = accountId;
        this.email = email;
        this.name = name;
        this.tel = tel;
        this.address = address;
        this.birthday = birthday;
        this.sex = sex;
    }

}
