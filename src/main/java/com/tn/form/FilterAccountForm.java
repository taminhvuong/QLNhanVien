package com.tn.form;

import com.tn.entity.Account;
import lombok.Data;

@Data
public class FilterAccountForm {
    private Account.Role role;
    private String nameDepartment;


}
