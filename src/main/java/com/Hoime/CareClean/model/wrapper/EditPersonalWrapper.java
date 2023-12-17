package com.Hoime.CareClean.model.wrapper;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Setter
@Getter
public class EditPersonalWrapper {
    private String name;
    private LocalDate birthday;
    private String sex;
    private String address;
    private String tel;
}
