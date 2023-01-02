package com.tn.dto.account;

import lombok.Data;

@Data
public class DepartmentDTOForm {
    private int id;
    private String name;

    public DepartmentDTOForm(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
