package com.tn.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tn.entity.Department;
import com.tn.entity.DepartmentTypeConvert;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
@Data
public class FilterDepartmentForm {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdDateMin;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdDateMax;
    private Department.Type type;
}
