package com.ticketsys.mgmt.domain;

import com.ticketsys.mgmt.constants.Priority;
import com.ticketsys.mgmt.constants.Status;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * ticket domain, this domain integrate with db through jpa.
 *
 * @author mdoss
 */
@Entity
@Table(name = "TS_Ticket_Details")
public class TicketInfo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ticket_id", unique = true, updatable = false, nullable = false)
    private int ticketId;
    @Column(name="user_id", length = 50)
    private String userId;
    private String ticket;
    @Column(name = "type", length = 20)
    private String type;
    @Column(name = "description", length = 1000)
    private String description;
    private boolean attached;
    @Column(name = "version", length = 10)
    private String version;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 20)
    private Status status;
    @Basic(optional = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date", updatable = false)
    private Date createdDate;
    @Column(name = "created_by", length = 50)
    private String createdBy;
    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_Date")
    private Date updatedDate;
    @Column(name = "updated_by", length = 50)
    private String updatedBy;
    @Type(type = "text")
    @Column(name = "file_base64")
    private String fileBase64;
    @Column(name = "file_extension", length = 5)
    private String fileExtension;
    @Enumerated(EnumType.STRING)
    @Column(name = "priority", length = 10)
    private Priority priority;

    public TicketInfo() {
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAttached() {
        return attached;
    }

    public void setAttached(boolean attached) {
        this.attached = attached;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getFileBase64() {
        return fileBase64;
    }

    public void setFileBase64(String fileBase64) {
        this.fileBase64 = fileBase64;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }
}
