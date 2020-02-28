package com.ticketsys.mgmt.domain;

import com.ticketsys.mgmt.constants.IssueType;
import com.ticketsys.mgmt.constants.Priority;
import com.ticketsys.mgmt.constants.Severity;
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
    @Column(name = "ticket", length = 500)
    private String ticket;
    @Enumerated(EnumType.STRING)
    @Column(name = "issue_type", length = 20)
    private IssueType issueType;
    @Column(name = "description", length = 1500)
    private String description;
    private boolean attached;
    @Column(name = "version", length = 10)
    private String version;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 20)
    private Status status;
    @Basic(optional = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date", updatable = false, nullable = false)
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
    private boolean active;
    @Enumerated(EnumType.STRING)
    @Column(name = "severity", length = 10)
    private Severity severity;
    private boolean createIssueInJira;

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

    public IssueType getIssueType() {
        return issueType;
    }

    public void setIssueType(IssueType issueType) {
        this.issueType = issueType;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Severity getSeverity() {
        return severity;
    }

    public void setSeverity(Severity severity) {
        this.severity = severity;
    }

    public boolean isCreateIssueInJira() {
        return createIssueInJira;
    }

    public void setCreateIssueInJira(boolean createIssueInJira) {
        this.createIssueInJira = createIssueInJira;
    }
}
