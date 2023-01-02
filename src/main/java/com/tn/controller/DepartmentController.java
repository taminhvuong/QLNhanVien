package com.tn.controller;

import com.tn.dto.account.AccountDTO;
import com.tn.dto.account.DepartmentDTO;
import com.tn.entity.Department;
import com.tn.form.FilterDepartmentForm;
import com.tn.form.FormCreateDepartment;
import com.tn.form.FormUpdateDepartment;
import com.tn.service.DepartmentServiceImpl;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("api/v1/departments")
@CrossOrigin("*")
public class DepartmentController {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private DepartmentServiceImpl departmentService;

    @GetMapping("")
    public ResponseEntity<?> getAll(Pageable pageable, @RequestParam(required = false) String search, FilterDepartmentForm form) {
        Page<Department> list = departmentService.findAll(pageable,search,form);
        List<DepartmentDTO> departmentDTOList = modelMapper.map(list.getContent(), new TypeToken<List<DepartmentDTO>>() {
        }.getType());
        Page<DepartmentDTO> dtoPages = new PageImpl<>(departmentDTOList, pageable, list.getTotalElements());
        return new ResponseEntity<>(dtoPages, HttpStatus.OK);

    }
    @GetMapping("getAllName")
    public ResponseEntity<?> getAllName() {
        List<Department> list = departmentService.fillAllName();
        List<DepartmentDTO> departmentDTOList = modelMapper.map(list, new TypeToken<List<DepartmentDTO>>() {
        }.getType());

        return new ResponseEntity<>(departmentDTOList, HttpStatus.OK);

    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findById( @PathVariable int id){
        Department department= departmentService.findById(id);
        DepartmentDTO departmentDTO=modelMapper.map(department,DepartmentDTO.class);

        return new ResponseEntity<>(departmentDTO,HttpStatus.OK);
    }
    @GetMapping("name/{name}")
    public ResponseEntity<?> existsByName( @PathVariable String name){
        return new ResponseEntity<>(departmentService.existsByName(name),HttpStatus.OK);
    }


    @Transactional
    @PostMapping("save")
    public ResponseEntity<?> save(@RequestBody FormCreateDepartment form) {
        departmentService.save(form);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }


    @PutMapping("update/{id}")
    public ResponseEntity<?> save(@PathVariable int id, @RequestBody FormUpdateDepartment formUpdate) {
        departmentService.updateDepartment(id, formUpdate);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable int id){
        departmentService.deleteById(id);
        return new ResponseEntity<>("delete success",HttpStatus.OK);
    }

    @DeleteMapping("delete")
    public ResponseEntity<?> deleteListId(@RequestParam List<Integer> ids){
        departmentService.deleteListId(ids);
        return new ResponseEntity<>("delete success",HttpStatus.OK);
    }
}
