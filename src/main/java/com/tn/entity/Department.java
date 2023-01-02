package com.tn.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table
@NoArgsConstructor
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)

    private String name;
    private int totalMember;
    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp


    private Date createdDate;

    @Convert(converter = DepartmentTypeConvert.class)
    private Type type;
    @OneToMany(mappedBy = "department")
    private List<Account> accounts;



    public enum Type {
        DEV("Dev"), TEST("Test"), SCRUM_MASTER("Scrum_Master"), PM("PM");
        private String value;

        private Type(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public static Type toEnum(String sqlValue) {
            for (Type type : Type.values()) {
                if (type.getValue().equals(sqlValue)) {
                    return type;
                }
            }
            return null;
        }
    }

    public Department(String name, Type type) {
        this.name = name;

        this.type = type;

    }
}
