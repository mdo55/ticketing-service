package com.ticketsys.mgmt.constants;

/**
 * Jira priority defined here.
 * @author mdoss.
 */
public enum Priority {

    NORMAL("NORMAL"),
    MEDIUM("MEDIUM"),
    HIGH("HIGH"),
    SEVERE("SEVERE");

    private final String name;

    private Priority(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }
}
