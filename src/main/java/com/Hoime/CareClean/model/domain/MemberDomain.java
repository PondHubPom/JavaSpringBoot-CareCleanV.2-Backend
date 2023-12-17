package com.Hoime.CareClean.model.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan
@Getter
@Setter
public class MemberDomain {
    private String message;
    private boolean isAdmin;
    private boolean isActive;
    private String accessToken = "";
    private String accountId;

}