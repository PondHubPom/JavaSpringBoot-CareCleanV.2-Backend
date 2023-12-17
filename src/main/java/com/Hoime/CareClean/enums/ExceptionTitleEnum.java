package com.Hoime.CareClean.enums;

public enum ExceptionTitleEnum {

    MEMBER_ERROR("Member Not Found");

    private final String title;

    ExceptionTitleEnum(String id) {
        this.title = id;
    }

    public String getTitle(){
        return title;
    }
}
