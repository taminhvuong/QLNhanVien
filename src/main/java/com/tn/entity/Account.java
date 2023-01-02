package com.tn.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(unique = true,nullable = false)
    private String username;
    private String firstName;
    @Column(name = "`password`", length = 250, nullable = false)
    private String password;
    private String lastName;
    @Formula(" concat(first_name, ' ', last_name)")
    private String fullName;
    @Enumerated(EnumType.STRING)
    private Role role;
    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;
public enum Role{
    ADMIN("ADMIN"),MANAGER("MANAGER"),EMPLOYEE("EMPLOYEE");
    private String value;
    private  Role(String value){
        this.value=value;
    }
    public String getValue(){
        return value;
    }
    public static Account.Role toEnum(String sqlValue) {
        for (Account.Role role : Account.Role.values()) {
            if (role.getValue().equals(sqlValue)) {
                return role;
            }
        }
        return null;
    }


}



    public Account(String username, String firstName, String password, String lastName) {
        this.username = username;
        this.firstName = firstName;
        this.password = password;
        this.lastName = lastName;
        this.fullName = firstName+" "+lastName;
        this.role = Role.EMPLOYEE;
    }
    public Account( String firstName, String password, String lastName,Account.Role role) {

        this.firstName = firstName;
        this.password = password;
        this.lastName = lastName;
        this.fullName = firstName+" "+lastName;
        this.role = role;
    }
}
