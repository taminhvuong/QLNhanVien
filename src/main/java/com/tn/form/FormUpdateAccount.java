package com.tn.form;

import com.tn.entity.Account;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FormUpdateAccount {
    private String firstName;
    private String username;
    private String lastName;
    private String password;
    private String role;
    private String nameDepartment;
}
