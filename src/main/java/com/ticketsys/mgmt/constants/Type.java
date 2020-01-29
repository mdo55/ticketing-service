package com.ticketsys.mgmt.constants;

/**
 * Jira type as enum constants.
 * @author mdoss
 */
public enum Type {

    BUG("BUG"),
    NEW ("NEW");

    private final String name;

    private Type(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
