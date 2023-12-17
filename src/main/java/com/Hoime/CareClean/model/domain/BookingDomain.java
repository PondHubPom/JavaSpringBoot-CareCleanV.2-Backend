package com.Hoime.CareClean.model.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan
@Getter
@Setter

public class BookingDomain {
    private String message;
    private String bookingId;
    private String accountId;
    private String statee;
}
