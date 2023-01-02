package com.tn.repository;

import com.tn.entity.Account;
import com.tn.entity.Department;
import com.tn.form.FormCreateAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account,Integer>, JpaSpecificationExecutor<Account> {
    @Query("from Account where id=:pid")
    Account findById(@Param("pid") int id);
    Account findByUsername(String username);

    List<Account> findByDepartment(Department name);
    //void save(FormCreateAccount form);
    void deleteById(int id);
    Boolean existsByUsername(String username);
    @Modifying
    @Transactional
    @Query("delete from Account where id IN (:ids)")
    void deleteListId(@Param("ids") List<Integer> ids);
}
