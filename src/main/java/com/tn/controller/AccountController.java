package com.tn.controller;

import com.tn.dto.account.AccountAddDepartment;
import com.tn.dto.account.AccountDTO;
import com.tn.entity.Account;
import com.tn.entity.Department;
import com.tn.form.FilterAccountForm;
import com.tn.form.FormCreateAccount;
import com.tn.form.FormUpdateAccount;
import com.tn.form.FormUpdatePassword;
import com.tn.service.AccountServiceImpl;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/accounts")
@CrossOrigin("*")
public class AccountController {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private AccountServiceImpl accountService;

    @GetMapping()
    public ResponseEntity<?> getAll(Pageable pageable, @RequestParam(required = false) String search, FilterAccountForm filterForm/*, @RequestParam(required = false) Account.Role role*/) {
        //  filterForm.setRole(role);
        Page<Account> list = accountService.findAll(pageable, search, filterForm);

//        List<AccountDTO> dtos = modelMapper.map(list.getContent(), new TypeToken<List<AccountDTO>>() {
//        }.getType());
        List<AccountDTO> dtos = new ArrayList<>();
        list.getContent().forEach(account -> {
            AccountDTO accountDTO = new AccountDTO();
            if (account.getDepartment() == null) {
                accountDTO = modelMapper.map(account, AccountDTO.class);
                accountDTO.setNameDepartment("");
            } else {
                accountDTO = modelMapper.map(account, AccountDTO.class);

            }
            dtos.add(accountDTO);
        });
        Page<AccountDTO> dtoPages = new PageImpl<>(dtos, pageable, list.getTotalElements());
       /* Page<AccountDTO> dtoPages=list.map(new Function<Account, AccountDTO>(){
         @Override
         public  AccountDTO apply(Account entity){
             AccountDTO dto=new AccountDTO();
             dto.setId(entity.getId());
             dto.setUsername(entity.getUsername());
             dto.setFullName(entity.getFullName());
             return dto;
         }
        });*/
//        List<AccountDTO> accountDTOList=new ArrayList<>();
//        list.forEach(account -> {
//            AccountDTO accountDTO=modelMapper.map(account,AccountDTO.class);
//          //  accountDTO.setDepartmentDTO(new DepartmentDTOForm(account.getDepartment().getId(),account.getDepartment().getName()));
//          accountDTOList.add(accountDTO);
//        });
        return new ResponseEntity<>(dtoPages, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAllId(@PathVariable int id) {
        Account account = accountService.findById(id);
        System.out.println("account "+account.getUsername());
        AccountDTO accountDTO = new AccountDTO();
        if (account.getDepartment() == null) {
            accountDTO = modelMapper.map(account, AccountDTO.class);
            accountDTO.setNameDepartment("");
        } else {

            accountDTO = modelMapper.map(account, AccountDTO.class);

        }

        return new ResponseEntity<>(accountDTO, HttpStatus.OK);
    }
    @GetMapping("getlistNull")
    public ResponseEntity<?> getAllId(Department department) {
        department=null;
        List<Account> account = accountService.findByNameDepartmentNull(department);
        List<AccountAddDepartment> accountDTO=modelMapper.map(account, new TypeToken<List<AccountAddDepartment>>() {
       }.getType());


        return new ResponseEntity<>(accountDTO, HttpStatus.OK);
    }
    @GetMapping("username/{username}")
    public ResponseEntity<?> existsByUsername(@PathVariable String username) {

        return new ResponseEntity<>(accountService.existsByUsername(username), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody FormCreateAccount form) {

        accountService.save(form);

        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody FormUpdateAccount formUpdateAccount) {

        accountService.updateAccount(id, formUpdateAccount);

        return new ResponseEntity<>("update success", HttpStatus.OK);
    }
    @PutMapping("/updatePassword/{id}")
    public ResponseEntity<?> updateupdatePassword(@PathVariable int id, @RequestBody FormUpdatePassword formUpdatePassword) {

        accountService.updatePassword(id, formUpdatePassword);

        return new ResponseEntity<>("update success", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        accountService.deleteById(id);
        return new ResponseEntity<>("delete success", HttpStatus.OK);
    }

    //delete list id
    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam List<Integer> ids) {
        accountService.deleteListId(ids);
        return new ResponseEntity<>("delete success", HttpStatus.OK);
    }
}
