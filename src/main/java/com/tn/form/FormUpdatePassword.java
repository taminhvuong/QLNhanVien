package com.tn.form;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class FormUpdatePassword {
    private String password;
}
