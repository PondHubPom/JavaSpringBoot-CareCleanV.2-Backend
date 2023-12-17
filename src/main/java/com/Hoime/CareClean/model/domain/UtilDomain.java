package com.Hoime.CareClean.model.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UtilDomain {
    private int code;
    private String message;

    public UtilDomain(int code , String message) {
        this.code = code;
        this.message = message;
    }
}