package com.ticketsys.mgmt.constants;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Jira status as enum constants.
 *
 * @author mdoss
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Status {
    @JsonProperty("Assigned")
    Assigned("Assigned"),
    @JsonProperty("Inprogress")
    Inprogress("Inprogress"),
    @JsonProperty("Open")
    Open("Open"),
    @JsonProperty("Closed")
    Closed("Closed");

    private final String name;

    private Status(String name) {
        this.name = name;
    }
    @JsonValue
    public String getName() {
        return this.name;
    }
}
