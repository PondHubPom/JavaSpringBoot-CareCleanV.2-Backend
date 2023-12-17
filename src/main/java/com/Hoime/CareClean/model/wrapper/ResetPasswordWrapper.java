package com.Hoime.CareClean.model.wrapper;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResetPasswordWrapper {
    private String oldPassword;
    private String newPassword;
}
