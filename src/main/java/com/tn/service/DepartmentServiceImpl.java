package com.tn.service;

import com.tn.entity.Account;
import com.tn.entity.Department;
import com.tn.form.FilterDepartmentForm;
import com.tn.form.FormCreateDepartment;
import com.tn.form.FormUpdateAccount;
import com.tn.form.FormUpdateDepartment;
import com.tn.repository.DepartmentRepository;
import com.tn.specification.AccountSpecification;
import com.tn.specification.DepartmentSpecification;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

import static org.hibernate.criterion.Restrictions.and;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override

    public Page<Department> findAll(Pageable pageable, String search, FilterDepartmentForm form) {

        Specification<Department> where = null;
        if (!StringUtils.isEmpty(search)) {
            DepartmentSpecification specification = new DepartmentSpecification("name", "LIKE", search);
            where = Specification.where(specification);
        }

        if (form != null && form.getType() != null) {
            DepartmentSpecification specificationType = new DepartmentSpecification("type", "=", form.getType());
            if (where == null) {
                where = specificationType;
            } else {
                where = where.and(specificationType);
            }
        }
        if (form != null && form.getCreatedDateMax() != null) {
            DepartmentSpecification specificationCreatedDate = new DepartmentSpecification("createdDate", "<=", form.getCreatedDateMax());
            if (where == null) {
                where = specificationCreatedDate;
            } else {
                where = where.and(specificationCreatedDate);
            }
        }
        if (form != null && form.getCreatedDateMin() != null) {
            DepartmentSpecification specificationCreatedDate = new DepartmentSpecification("createdDate", ">=", form.getCreatedDateMin());
            if (where == null) {
                where = specificationCreatedDate;
            } else {
                where = where.and(specificationCreatedDate);
            }
        }

        return departmentRepo.findAll(where, pageable);
    }

    @Override
    public List<Department> fillAllName() {
        return departmentRepo.findAll();
    }

    @Override
    public Department findById(int id) {
        return departmentRepo.findById(id);
    }

    @Override
    public Department findByName(String name) {
        return departmentRepo.findByName(name);
    }

    @Override
    public void save(FormCreateDepartment form) {
        //   Department department=new Department(form.getName(),(form.getType()));
        Department department = modelMapper.map(form, Department.class);
        departmentRepo.save(department);
    }

    @Override
    public void deleteById(int id) {
        departmentRepo.deleteById(id);
    }

    @Override
    public boolean existsByName(String name) {
        return departmentRepo.existsByName(name);
    }

    @Override
    public void updateDepartment(int id, FormUpdateDepartment formUpdateDe) {
        Department department = departmentRepo.findById(id);
        department.setName(formUpdateDe.getName());

        department.setType(formUpdateDe.getType());
        departmentRepo.save(department);
    }

    @Override
    public void deleteListId(List<Integer> ids) {
        departmentRepo.deleteListId(ids);
    }


}
