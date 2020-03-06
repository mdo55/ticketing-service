package com.ticketsys.mgmt.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @author mdoss
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(description = "Details of ticket employee")
public class TicketInfoRequest {
    @ApiModelProperty(notes = "ticketId is unique for each ticket")
    private int ticketId;
    private String userId;
    @ApiModelProperty(notes = "ticket is mandatory", required = true)
    @Size(min = 3, max = 500, message = "ticket size minimum=3 and maximum=500")
    @NotBlank
    @NotNull(message = "Ticket is mandatory")
    private String ticket;
    @ApiModelProperty(notes = "type of ticket {BUG, NewFeature}")
    private String type;
    @ApiModelProperty(notes = "desceiption is mandatory", required = true)
    @Size(min = 3, max = 1500, message = "description size minimum=3 and maximum=1500")
    @NotBlank
    @NotNull(message = "Description is mandatory")
    private String description;
    @ApiModelProperty(notes = "if file attached this field is true or else false")
    private boolean attached;
    @ApiModelProperty(notes = "version")
    private String version;
    @ApiModelProperty(notes = "One of the values accepted for status class: [Inprogress, Closed, Assigned, Open]", example = "Open")
//    @NotBlank(message = "One of the values accepted for status class: [INPROGRESS, CLOSED, ASSIGNED, OPEN]")
    private String status;
    @ApiModelProperty(notes = "ticket created time")
    private Date createdDate;
    @ApiModelProperty(notes = "ticket created by who")
    private String createdBy;
    @ApiModelProperty(notes = "update ticket time")
    private Date updatedDate;
    @ApiModelProperty(notes = "ticket updated by who")
    private String updatedBy;
    @ApiModelProperty(notes = "the uploaded image encoded by base64 text")
    private String fileBase64;
    @ApiModelProperty(notes = "file extention {.png, .jpg etc} stored")
    private String fileExtension;
    @ApiModelProperty(notes = "priority of the ticket")
    private String priority;
    @ApiModelProperty(notes = "once the ticket status 'CLOSED' the active field hold true value or else false")
    private boolean active;
    @ApiModelProperty(notes = "severity like [Critical, High, Medium, Low]")
    private String severity;
    @ApiModelProperty(notes = "createIssueInJira field will be true, if the issue will created in jira else false.")
    private boolean createIssueInJira;
    @ApiModelProperty(notes = "jira projectKey and issueKey will stored in jiraIssueInfo field.")
    private String jiraIssueInfo;
}
