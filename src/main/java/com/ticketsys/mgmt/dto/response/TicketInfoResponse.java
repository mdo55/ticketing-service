package com.ticketsys.mgmt.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ticketsys.mgmt.constants.Priority;
import com.ticketsys.mgmt.constants.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author mdoss
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TicketInfoResponse {
    private int ticketId;
    private String userId;
    private String ticket;
    private String type;
    private String description;
    private boolean attached;
    private String version;
    private String status;
    private Date createdDate;
    private String createdBy;
    private Date updatedDate;
    private String updatedBy;
    private String fileBase64;
    private String fileExtension;
    private String priority;
    private boolean active;
    private String severity;
    private boolean createIssueInJira;
    private String jiraIssueInfo;
}
