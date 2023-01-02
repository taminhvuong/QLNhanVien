package com.tn.specification;

import com.tn.entity.Account;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class AccountSpecification implements Specification<Account> {
    private String field;
    private String operator;
    private Object value;

    public AccountSpecification(String field, String operator, Object value) {
        this.field = field;
        this.operator = operator;
        this.value = value;
    }

    @Override
    public Predicate toPredicate(Root<Account> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (field.equalsIgnoreCase("fullName") || field.equalsIgnoreCase("username")) {
            if (operator.equals("LIKE")) {
                return criteriaBuilder.like(root.get(field), "%" + value.toString() + "%");
            }
        }
        if (field.equalsIgnoreCase("role")) {
            return criteriaBuilder.equal(root.get("role"), value);
        }
        if (field.equalsIgnoreCase("nameDepartment") || field.equalsIgnoreCase("username")) {
            if (operator.equals("=")) {
                return criteriaBuilder.like(root.get("department").get("name"), value.toString() );
            }
        }
        return null;
    }
}
