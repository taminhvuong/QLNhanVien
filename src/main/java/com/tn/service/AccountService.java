package com.tn.service;

import com.tn.entity.Account;
import com.tn.entity.Department;
import com.tn.form.FilterAccountForm;
import com.tn.form.FormCreateAccount;
import com.tn.form.FormUpdateAccount;
import com.tn.form.FormUpdatePassword;
import com.tn.specification.AccountSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface AccountService extends UserDetailsService {
    public Page<Account> findAll(Pageable pageable, String search, FilterAccountForm filterForm);
    public Account findById(int id);
    public boolean existsByUsername(String username);
    public Account findByUsename(String username);
    public List<Account> findByNameDepartmentNull(Department name);
    public void save(FormCreateAccount form);
    public void updateAccount(int id,FormUpdateAccount form);
    public void updatePassword(int id, FormUpdatePassword form);
    public void deleteById(int id);
    public void  deleteListId(List<Integer> ids);
}
