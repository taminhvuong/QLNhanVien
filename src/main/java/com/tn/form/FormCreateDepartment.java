package com.tn.form;

import com.tn.entity.Department;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
@Data
public class FormCreateDepartment {
    private String name;
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createdDate;
    private Department.Type type;

}
