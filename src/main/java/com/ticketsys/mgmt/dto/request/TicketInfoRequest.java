package com.ticketsys.mgmt.dto.request;

import com.ticketsys.mgmt.constants.Priority;
import com.ticketsys.mgmt.constants.Status;
import lombok.Data;

import java.util.Date;

/**
 * @author mdoss
 */
@Data
public class TicketInfoRequest {
    private int ticketId;
    private String userId;
    private String ticket;
    private String type;
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
}
