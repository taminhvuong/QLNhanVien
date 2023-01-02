package com.tn.service;

import com.tn.entity.Department;
import com.tn.form.FilterDepartmentForm;
import com.tn.form.FormCreateDepartment;
import com.tn.form.FormUpdateAccount;
import com.tn.form.FormUpdateDepartment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DepartmentService {
    public Page<Department> findAll(Pageable pageable, String search, FilterDepartmentForm form);
public List<Department> fillAllName();
    public Department findById(int id);

    public Department findByName(String name);

    public void save(FormCreateDepartment form);
    public void deleteById(int id);
   public boolean existsByName(String name);

    public void updateDepartment(int id,FormUpdateDepartment formUpdateDe);
    void deleteListId( List<Integer> ids);
}
