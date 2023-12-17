package com.Hoime.CareClean.model.wrapper;

import com.Hoime.CareClean.model.entity.BookingEntity;
import com.Hoime.CareClean.model.entity.MemberEntity;
import com.Hoime.CareClean.services.UtilService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
public class BookingSuccessWrapper{
    private String BookingId;
    private String AccountId;

}
