package com.ticketsys.mgmt.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppIssue {
    private String issueKey;
    private String issueType;
    private String component;
    private String summary;
    private String description;
    private String reporter;
    private int priorityId;
    private int issueTypeId;
    private int ticketId;
    private boolean fileAttached;
}
