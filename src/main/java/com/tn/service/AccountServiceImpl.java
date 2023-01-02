package com.tn.service;

import com.tn.entity.Account;
import com.tn.entity.Department;
import com.tn.form.FilterAccountForm;
import com.tn.form.FormCreateAccount;
import com.tn.form.FormUpdateAccount;
import com.tn.form.FormUpdatePassword;
import com.tn.repository.AccountRepository;
import com.tn.repository.DepartmentRepository;
import com.tn.specification.AccountSpecification;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private DepartmentRepository departmentRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepo.findByUsername(username);
        if (account == null) {
            throw new UsernameNotFoundException("Account not found" + username);
        }
//        new BCryptPasswordEncoder().encode("123456");
        return new User(username
                , account.getPassword()
                , AuthorityUtils.createAuthorityList(String.valueOf(account.getRole())));
    }

    @Override
    @SuppressWarnings("deprecation")
    public Page<Account> findAll(Pageable pageable, String search, FilterAccountForm filterForm) {

        Specification<Account> where = null;
        if (!StringUtils.isEmpty(search)) {
            //  if (filterForm != null && filterForm.getRole() != null) {
            AccountSpecification searchSpecification = new AccountSpecification("username", "LIKE", search);
            where = Specification.where(searchSpecification);
        }
        if (filterForm != null && filterForm.getRole() != null) {
            AccountSpecification role = new AccountSpecification("role", "=", filterForm.getRole());
            if (where == null) {
                where = role;
            } else {
                where = where.and(role);
            }
        }
        if(filterForm != null && filterForm.getNameDepartment()!= null){
            AccountSpecification departmentName=new AccountSpecification("nameDepartment","=",filterForm.getNameDepartment());
            if (where == null) {
                where = departmentName;
            } else {
                where = where.and(departmentName);
            }
        }
        return accountRepo.findAll(where, pageable);
    }

    @Override
    public Account findById(int id) {

        return accountRepo.findById(id);
    }

    @Override
    public boolean existsByUsername(String username) {
        return accountRepo.existsByUsername(username);
    }

    @Override
    public Account findByUsename(String username) {
        return accountRepo.findByUsername(username);
    }

    @Override
    public List<Account> findByNameDepartmentNull(Department name) {
        return accountRepo.findByDepartment(name);
    }

    @Override
    public void save(FormCreateAccount form) {
        Department acc = departmentRepo.findByName(form.getNameDepartment());
        //convert form ->entity
        Account account = modelMapper.map(form, Account.class);
        account.setPassword(new BCryptPasswordEncoder().encode(form.getPassword()));

        account.setDepartment(acc);

        accountRepo.save(account);
    }

    @Override
    public void updateAccount(int id, FormUpdateAccount form) {
        Account account = accountRepo.findById(id);
//        account=modelMapper.map(form,Account.class);
        account.setUsername(form.getUsername());
        account.setFirstName(form.getFirstName());
        account.setLastName(form.getLastName());
        account.setPassword(new BCryptPasswordEncoder().encode(form.getPassword()));
        account.setRole(Account.Role.valueOf(form.getRole()));
        if(form.getNameDepartment()!=null) {
            account.setDepartment(departmentRepo.findByName(form.getNameDepartment()));
        }

        accountRepo.save(account);
    }

    @Override
    public void updatePassword(int id, FormUpdatePassword form) {
        Account account = accountRepo.findById(id);
//        account=modelMapper.map(form,Account.class);

        account.setPassword(new BCryptPasswordEncoder().encode(form.getPassword()));


        accountRepo.save(account);
    }

    @Override
    public void deleteById(int id) {
        accountRepo.deleteById(id);
    }

    @Override
    public void deleteListId(List<Integer> ids) {
        accountRepo.deleteListId(ids);
    }
}
