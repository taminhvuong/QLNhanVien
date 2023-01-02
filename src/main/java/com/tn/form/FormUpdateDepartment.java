package com.tn.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tn.entity.Department;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class FormUpdateDepartment {
    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createdDate;
    private Department.Type type;
}
