package ru.aurakhov.mysecondtestapspringbootlr5.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum SystemsName {
    ERP("Enterprise Resource Planning"),
    CRM("Customer Relationship Management"),
    ABC("123"),
    WMS("Warehouse Management System");


    private final String name;


    SystemsName(String name) {
        this.name = name;
    }

    @JsonValue
    public String getName() {
        return name;
    }

    @Override
    public String toString() {

        return name;
    }
}
