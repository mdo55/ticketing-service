package com.ticketsys.mgmt.constants;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Jira priority defined here.
 * @author mdoss.
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Priority {
//    Highest, High, Medium, Low
    Low("Low"),
    Medium("Medium"),
    High("High"),
    Highest("Highest");

    private final String name;
    private final int idArr[] = {4, 3, 2, 1};
    private Priority(String name) {
        this.name = name;
    }
    @JsonValue
    public String getName() {
        return this.name;
    }

    /**
     * jira priority id.
     * @return int.
     */
    public int getId() {
        return this.idArr[this.ordinal()];
    }
}
