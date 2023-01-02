package com.tn.dto.auth;

import com.tn.entity.Account;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
@Data
@NoArgsConstructor
public class LoginAccountDTO {
private int id;
    private String username;
    private String fullName;
    private Account.Role role;

    public LoginAccountDTO(int id, String fullName, Account.Role role) {
        this.id = id;
        this.fullName = fullName;
        this.role = role;
    }
}
