package com.ticketsys.mgmt.util;

import com.ticketsys.mgmt.constants.Priority;
import com.ticketsys.mgmt.constants.Severity;
import com.ticketsys.mgmt.constants.Status;
import com.ticketsys.mgmt.constants.IssueType;
import com.ticketsys.mgmt.domain.TicketInfo;
import com.ticketsys.mgmt.dto.request.TicketInfoRequest;
import com.ticketsys.mgmt.dto.response.TicketInfoResponse;

import java.util.*;

/**
 * @author mdoss
 */
public class MapperUtil {
    private static MapperUtil _INSTANCE = new MapperUtil();
    private MapperUtil() {}
    public static MapperUtil getInstance() {
        if(Objects.isNull(_INSTANCE)) {
            _INSTANCE = new MapperUtil();
            return _INSTANCE;
        }
        return _INSTANCE;
    }
    /**
     * map request to domain (ticketInfo) object.
     * @param requestDto
     * @return ticketInfo
     */
    public TicketInfo mapRequestToTicketInfoDomain(TicketInfoRequest requestDto, TicketInfo ticketInfo) {
        if(Objects.nonNull(ticketInfo)) {
            ticketInfo.setUpdatedDate(new Date(System.currentTimeMillis()));
        }else {
            ticketInfo = new TicketInfo();
            ticketInfo.setUserId(requestDto.getUserId());
            ticketInfo.setCreatedDate(new Date(System.currentTimeMillis()));
        }
        ticketInfo.setTicket(requestDto.getTicket());
        ticketInfo.setIssueType(Objects.nonNull(requestDto.getType()) ? IssueType.valueOf(requestDto.getType()) : IssueType.Bug);
        ticketInfo.setDescription(requestDto.getDescription());
        ticketInfo.setAttached(requestDto.isAttached());
        ticketInfo.setVersion(requestDto.getVersion());
        ticketInfo.setStatus(Objects.nonNull(requestDto.getStatus()) ? Status.valueOf(requestDto.getStatus()) : Status.Open);
        ticketInfo.setCreatedBy(requestDto.getCreatedBy());
        ticketInfo.setUpdatedBy(requestDto.getUpdatedBy());
        ticketInfo.setFileBase64(requestDto.getFileBase64());
        ticketInfo.setFileExtension(requestDto.getFileExtension());
        Priority priority = requestDto.getPriority() == null ? Priority.Low : Priority.valueOf(requestDto.getPriority());
        ticketInfo.setPriority(priority);
        ticketInfo.setActive(requestDto.isActive());
        Severity severity = Objects.nonNull(requestDto.getSeverity()) ? Severity.valueOf(requestDto.getSeverity()): Severity.Low;
        ticketInfo.setSeverity(Severity.valueOf(requestDto.getSeverity()));
        ticketInfo.setCreateIssueInJira(requestDto.isCreateIssueInJira());
        return ticketInfo;
    }
    /**
     * map domain to response object.
     * @param entity
     * @return ticketInfoResponse.
     */
    public TicketInfoResponse mapTicketInfoDomainToResponse(TicketInfo entity) {
        if(Objects.isNull(entity)) {
            return null;
        }
        TicketInfoResponse responseDto = new TicketInfoResponse();
        responseDto.setTicketId(entity.getTicketId());
        responseDto.setUserId(entity.getUserId());
        responseDto.setTicket(entity.getTicket());
        responseDto.setType(Objects.nonNull(entity.getIssueType()) ? entity.getIssueType().getName() : null);
        responseDto.setDescription(entity.getDescription());
        responseDto.setAttached(entity.isAttached());
        responseDto.setVersion(entity.getVersion());
        responseDto.setStatus(Objects.nonNull(entity.getStatus()) ? entity.getStatus().getName() : null);
        responseDto.setCreatedDate(entity.getCreatedDate());
        responseDto.setCreatedBy(entity.getCreatedBy());
        responseDto.setUpdatedDate(entity.getUpdatedDate());
        responseDto.setUpdatedBy(entity.getUpdatedBy());
        responseDto.setFileBase64(entity.getFileBase64());
        responseDto.setFileExtension(entity.getFileExtension());
        responseDto.setPriority(Objects.nonNull(entity.getPriority())? entity.getPriority().getName(): null);
        responseDto.setActive(entity.isActive());
        responseDto.setSeverity(Objects.nonNull(entity.getSeverity())? entity.getSeverity().getName() : null);
        responseDto.setCreateIssueInJira(entity.isCreateIssueInJira());
        return responseDto;
    }

    /**
     *
     * @param ticketInfoList
     * @return
     */
    public List<TicketInfoResponse> mapDomainToResponseList(List<TicketInfo> ticketInfoList) {
        if(Objects.isNull(ticketInfoList) || ticketInfoList.isEmpty()) {
            return Collections.emptyList();
        }
        List<TicketInfoResponse> responseList = new ArrayList<>();
        for (TicketInfo entity : ticketInfoList) {
            responseList.add(mapTicketInfoDomainToResponse(entity));
        }
        return responseList;
    }
}
