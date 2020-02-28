package com.ticketsys.mgmt.constants;

public enum Severity {
//    Critical, High, Medium, Low
    Critical("Critical"),
    High("High"),
    Medium("Medium"),
    Low("Low");

    private final String name;

    private Severity(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }
}
