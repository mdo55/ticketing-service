package com.ticketsys.mgmt.service;

import com.ticketsys.mgmt.constants.Status;
import com.ticketsys.mgmt.dto.request.TicketInfoRequest;
import com.ticketsys.mgmt.dto.response.TicketInfoResponse;
import org.springframework.data.domain.Page;

/**
 * ticket service.
 * @author mdoss
 */
public interface TicketService {
    public TicketInfoResponse save(TicketInfoRequest requestDto);
    TicketInfoResponse findById(Integer Id);
    Page<TicketInfoResponse> loadPage(int page, int size);
    TicketInfoResponse updateTicket(TicketInfoRequest requestDto);
    void cancelTicket(Integer ticketId, Status status);
    long getTicketsCountFindByUserId(String userId);
    long getTicketsCount();

}
