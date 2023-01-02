package com.tn.dto.account;

import com.tn.entity.Account;
import lombok.Data;
import org.hibernate.annotations.Formula;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;
@Data
public class AccountDTO implements Serializable {
    private int id;
    private String username;
    private String fullName;
    private String firstName;
    private String lastName;
    private String password;
    private String role;
   private  String nameDepartment;

   // private DepartmentDTOForm departmentDTO;
}
