package com.ticketsys.mgmt.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ticketsys.mgmt.constants.Priority;
import com.ticketsys.mgmt.constants.Status;
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
public class TicketInfoRequest {
    private int ticketId;
    private String userId;
    @Size(min = 3, max = 500, message = "ticket size minimum=3 and maximum=500")
    @NotBlank
    @NotNull(message = "Ticket is mandatory")
    private String ticket;
    private String type;
    @Size(min = 3, max = 1500, message = "description size minimum=3 and maximum=1500")
    @NotBlank
    @NotNull(message = "Description is mandatory")
    private String description;
    private boolean attached;
    private String version;
    private Status status;
    private Date createdDate;
    private String createdBy;
    private Date updatedDate;
    private String updatedBy;
    private String fileBase64;
    private String fileExtension;
    private Priority priority;
    private boolean active;
}
