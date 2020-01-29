package com.ticketsys.mgmt.util;

import com.ticketsys.mgmt.constants.Status;
import com.ticketsys.mgmt.domain.TicketInfo;
import com.ticketsys.mgmt.dto.request.TicketInfoRequest;
import com.ticketsys.mgmt.dto.response.TicketInfoResponse;

import java.util.Date;

/**
 * @author mdoss
 */
public class MapperUtil {
    private MapperUtil() {}

    /**
     * map request to domain (ticketInfo) object.
     * @param requestDto
     * @return ticketInfo
     */
    public static TicketInfo mapRequestToTicketInfoDomain(TicketInfoRequest requestDto) {
        TicketInfo ticketInfo = new TicketInfo();
        ticketInfo.setUserId(requestDto.getUserId());
        ticketInfo.setTicket(requestDto.getTicket());
        ticketInfo.setType(requestDto.getType());
        ticketInfo.setDescription(requestDto.getDescription());
        ticketInfo.setAttached(requestDto.isAttached());
        ticketInfo.setVersion(requestDto.getVersion());
        ticketInfo.setStatus(Status.valueOf(requestDto.getStatus()));
        ticketInfo.setCreatedDate(new Date(System.currentTimeMillis()));
        ticketInfo.setCreatedBy("mdoss@altimetrik.com");
        ticketInfo.setUpdatedDate(new Date(System.currentTimeMillis()));
        ticketInfo.setUpdatedBy("UpdatedBy");
        ticketInfo.setFileBase64(requestDto.getFileBase64());
        return ticketInfo;
    }
    /**
     * map domain to response object.
     * @param entity
     * @return ticketInfoResponse.
     */
    public static TicketInfoResponse mapTicketInfoDomainToResponse(TicketInfo entity) {
        TicketInfoResponse responseDto = new TicketInfoResponse();
        responseDto.setTicketId(entity.getTicketId());
        responseDto.setUserId(entity.getUserId());
        responseDto.setTicket(entity.getTicket());
        responseDto.setType(entity.getType());
        responseDto.setDescription(entity.getDescription());
        responseDto.setAttached(entity.isAttached());
        responseDto.setVersion(entity.getVersion());
        responseDto.setStatus(entity.getStatus().getName());
        responseDto.setCreatedDate(entity.getCreatedDate());
        responseDto.setCreatedBy(entity.getCreatedBy());
        responseDto.setUpdatedDate(entity.getUpdatedDate());
        responseDto.setUpdatedBy(entity.getUpdatedBy());
        responseDto.setFileBase64(entity.getFileBase64());
        return responseDto;
    }
}
