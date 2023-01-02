package com.tn.controller;

import com.tn.dto.auth.LoginAccountDTO;
import com.tn.entity.Account;
import com.tn.service.AccountService;
import com.tn.service.AccountServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.function.Predicate;
@RestController
@RequestMapping(value = "api/v1/login")
@CrossOrigin("*")
public class LoginController {

    @Autowired
    private AccountService service;
    @Autowired
    private ModelMapper modelMapper;
    @GetMapping()
    public ResponseEntity<?> login(Principal principal) {

        String username = principal.getName();
        Account entity = service.findByUsename(username);

        // convert entity --> dto
        LoginAccountDTO dto = modelMapper.map(entity,LoginAccountDTO.class);
               // new LoginAccountDTO(entity.getId(), entity.getFullName());

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}