package com.ticketsys.mgmt.constants;

/**
 * Jira status as enum constants.
 *
 * @author mdoss
 */
public enum Status {

    ASSIGNED("ASSIGNED"),
    INPROGRESS("INPROGRESS"),
    OPEN("OPEN"),
    CLOSED("CLOSED");

    private final String name;

    private Status(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
