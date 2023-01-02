package com.tn.repository;

import com.tn.entity.Account;
import com.tn.entity.Department;
import com.tn.form.FormCreateDepartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Integer>, JpaSpecificationExecutor<Department> {

    Department findById(int id);
    Department findByName(String name);
Boolean existsByName(String name);
    void deleteById(int id);
    @Transactional
    @Modifying
    @Query("delete from Department where id IN (:ids)")
    void deleteListId(@Param("ids") List<Integer> ids);
}
