package com.tn.form;

import com.tn.entity.Account;
import com.tn.entity.Department;
import lombok.Data;
import org.hibernate.annotations.Formula;

import javax.persistence.*;

@Data
public class FormCreateAccount {

    private String username;
    private String firstName;
    private String password;
    private String lastName;
    private Account.Role role;
   private String nameDepartment;

}
