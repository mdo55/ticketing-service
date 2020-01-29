package com.ticketsys.mgmt.impl;

import com.ticketsys.mgmt.domain.TicketInfo;
import com.ticketsys.mgmt.dto.request.TicketInfoRequest;
import com.ticketsys.mgmt.dto.response.TicketInfoResponse;
import com.ticketsys.mgmt.repository.TicketRepository;
import com.ticketsys.mgmt.service.TicketService;
import com.ticketsys.mgmt.util.MapperUtil;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

/**
 * ticket service implementation.
 *
 * @author mdoss
 */
@Service
@NoArgsConstructor
@Slf4j
public class TicketServiceImpl implements TicketService {

    @Autowired
    @NotNull
    private TicketRepository ticketRepository;

    /**
     * ticketInfo request map to domain (TicketInfo) object.
     * save transient object.
     * @param requestDto
     * @return ticketInfoResponse.
     */
    @Override
    public TicketInfoResponse save(TicketInfoRequest requestDto) {
       TicketInfo entity = MapperUtil.mapRequestToTicketInfoDomain(requestDto);
       ticketRepository.save(entity);
       return MapperUtil.mapTicketInfoDomainToResponse(entity);
    }

    /**
     * ticketInfo domain find by id, finally map to response object.
     * @param id
     * @return ticketInfoResponse.
     */
    @Override
    public TicketInfoResponse findById(Integer id) {
        TicketInfo entity = ticketRepository.findById(id).get();
        return MapperUtil.mapTicketInfoDomainToResponse(entity);
    }
}
