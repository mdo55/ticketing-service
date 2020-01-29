package com.ticketsys.mgmt.dto.response;

import lombok.Data;

import java.util.Date;

/**
 * @author mdoss
 */
@Data
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
}
