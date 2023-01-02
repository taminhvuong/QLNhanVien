package com.tn.specification;

import com.tn.entity.Department;
import lombok.SneakyThrows;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DepartmentSpecification implements Specification<Department> {
    private String field;
    private String operator;
    private Object value;

    public DepartmentSpecification(String field, String operator, Object value) {
        this.field = field;
        this.operator = operator;
        this.value = value;
    }

    @SneakyThrows
    @Override
    public Predicate toPredicate(Root<Department> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if(field.equalsIgnoreCase("name")){
           return criteriaBuilder.like(root.get(field),"%"+value.toString()+"%");
        }
        if(field.equalsIgnoreCase("type")){
            return criteriaBuilder.equal(root.get(field),value);
        }
        SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
        if(field.equalsIgnoreCase("createdDate")) {
            if (operator.equals(">=")) {
                return criteriaBuilder.greaterThanOrEqualTo(root.get(field), (Date)value);
            }


            if (operator.equals("<=")) {
                return criteriaBuilder.lessThanOrEqualTo(root.get(field),(Date) value);
            }
        }
        return null;
    }
}
