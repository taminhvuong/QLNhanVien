package com.tn.entity;

import javax.persistence.AttributeConverter;

public class AccountTypeConvert implements AttributeConverter<Account.Role, String>{

    @Override
    public String convertToDatabaseColumn(Account.Role role) {
        if (role == null) {
            return null;
        }
        return role.getValue();
    }

    @Override
    public Account.Role convertToEntityAttribute(String sqlValue) {
        if (sqlValue == null) {
            return null;
        }
        return Account.Role.toEnum(sqlValue);
    }
}

