package com.Hoime.CareClean.model.domain;

import com.Hoime.CareClean.model.entity.HistoryBookingEntity;
import com.Hoime.CareClean.model.entity.MemberEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;


@Getter
@Setter
public class MemberListDomain {
    private String email;
    private String name;
    private String tel;
    private String birthday;
    private String address;
    private String sex;
    private String accountId;
    private boolean Active;

    public MemberListDomain() {
    }

    public MemberListDomain(MemberEntity e) {
        this.email = e.getEmail();
        this.name = e.getName();
        this.tel = e.getTel();
        this.birthday = e.getBirthday().toString();
        this.address = e.getAddress();
        this.sex = e.getSex();
        this.accountId = e.getId();
        this.Active = e.isActive();
    }
}
