package com.tn.dto.account;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tn.entity.Account;
import com.tn.entity.Department;
import com.tn.entity.DepartmentTypeConvert;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.Date;
@Data
public class DepartmentDTO {
    private int id;
    private String name;
    private int totalMember;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createdDate;
    private Department.Type type;
    public DepartmentDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public DepartmentDTO() {

    }

}
