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
    NewFeature ("New Feature");

    private final String name;

    private IssueType(String name) {
        this.name = name;
    }
    @JsonValue
    public String getName() {
        return this.name;
    }
}
