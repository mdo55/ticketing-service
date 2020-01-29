package com.ticketsys.mgmt.service;

import com.ticketsys.mgmt.dto.request.TicketInfoRequest;
import com.ticketsys.mgmt.dto.response.TicketInfoResponse;

/**
 * ticket service.
 * @author mdoss
 */
public interface TicketService {
    public TicketInfoResponse save(TicketInfoRequest requestDto);
    TicketInfoResponse findById(Integer Id);
}
