package com.ticketsys.mgmt.constants;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Jira type as enum constants.
 * @author mdoss
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum IssueType {
// Bug, New Feature
    Bug("Bug"),
    NewFeature ("NewFeature");

    private final String name;
    private int idArr[] = {10005, 10004};
    private IssueType(String name) {
        this.name = name;
    }
    @JsonValue
    public String getName() {
        return this.name;
    }

    /**
     * jira issueType id
     * @return int.
     */
    public int getId() {
        return this.idArr[this.ordinal()];
    }
}
