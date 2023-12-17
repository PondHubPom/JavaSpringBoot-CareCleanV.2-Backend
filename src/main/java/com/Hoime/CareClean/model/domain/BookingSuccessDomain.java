package com.Hoime.CareClean.model.domain;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan
@Getter
@Setter
public class BookingSuccessDomain {
    private String message;
    private String statee;

    public BookingSuccessDomain() {}

    public BookingSuccessDomain(String m,String s) {
        this.message = m;
        this.statee = s;

    }
}
