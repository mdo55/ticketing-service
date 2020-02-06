package com.ticketsys.mgmt.impl;

import com.ticketsys.mgmt.constants.ErrorCode;
import com.ticketsys.mgmt.constants.Status;
import com.ticketsys.mgmt.domain.TicketInfo;
import com.ticketsys.mgmt.dto.request.TicketInfoRequest;
import com.ticketsys.mgmt.dto.response.TicketInfoResponse;
import com.ticketsys.mgmt.exception.TicketServiceException;
import com.ticketsys.mgmt.repository.TicketRepository;
import com.ticketsys.mgmt.service.TicketService;
import com.ticketsys.mgmt.util.MapperUtil;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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
        try {
            TicketInfo entity = MapperUtil.getInstance().mapRequestToTicketInfoDomain(requestDto, null);
            ticketRepository.save(entity);
            return MapperUtil.getInstance().mapTicketInfoDomainToResponse(entity);
        }catch (Exception exception) {
            log.error("Exception in save() method: " + exception);
            throw new TicketServiceException(exception);
        }
    }

    /**
     * ticketInfo domain find by id, finally map to response object.
     * @param ticketId
     * @return ticketInfoResponse.
     */
    @Override
    public TicketInfoResponse findById(Integer ticketId) {
        try {
            Optional<TicketInfo> persistObj = ticketRepository.findById(ticketId);
            if (Objects.nonNull(persistObj)) {
                return MapperUtil.getInstance().mapTicketInfoDomainToResponse(persistObj.get());
            }
            return null;
        }catch (Exception exception) {
            log.error("Exception in findById() method: " + exception);
            throw new TicketServiceException(exception);
        }
    }

    /**
     * Pagination data loaded.
     * @param page
     * @param size
     * @return page<TicketInfoResponse> object.
     */
    @Override
    public Page<TicketInfoResponse> loadPage(int page, int size) {
//        Page<TicketInfo> ticketPage = ticketRepository.findAll(PageRequest.of(page, size, Sort.by("ticketId").descending()));
        try {
            List<TicketInfo> infoList = ticketRepository.findAll(Sort.by("ticketId").descending());
            if (Objects.isNull(infoList) || infoList.isEmpty()) {
                return Page.empty();
            } else {
                List<TicketInfoResponse> responseList = infoList.stream()
                        .map(ticketInfo -> MapperUtil.getInstance().mapTicketInfoDomainToResponse(ticketInfo))
                        .collect(Collectors.toList());
                Page<TicketInfoResponse> pages = new PageImpl<>(responseList, PageRequest.of(0, 6), 15);
                return pages;
            }
        }catch (Exception exception) {
            log.error("Exception in loadPage() method: " + exception);
            throw new TicketServiceException(exception);
        }
    }

    /**
     * the ticket updated.
     * @param requestDto
     * @return ticketInfoResponse.
     */
    @Override
    public TicketInfoResponse updateTicket(TicketInfoRequest requestDto) {
        try {
            if (Objects.isNull(requestDto.getTicketId())) {
                log.info("Updated model ticketId is null.");
                ErrorCode errorCode = ErrorCode.ERR_BADREQ400;
                throw new TicketServiceException(errorCode.getErrorCode(), errorCode.getMessage());
            }
            Optional<TicketInfo> persistObj = ticketRepository.findById(requestDto.getTicketId());
            if (Objects.nonNull(persistObj)) {
                TicketInfo persistEntity = persistObj.get();
                MapperUtil.getInstance().mapRequestToTicketInfoDomain(requestDto, persistEntity);
                ticketRepository.saveAndFlush(persistEntity);
                return MapperUtil.getInstance().mapTicketInfoDomainToResponse(persistEntity);
            }
            return null;
        }catch (RuntimeException exception) {
            log.error("Exception in updateTicket() method: " + exception);
            throw new TicketServiceException(exception);
        }
    }

    /**
     * delete/close/inActive ticket.
     * @param ticketId
     * @param status
     */
    @Override
    public void cancelTicket(Integer ticketId, Status status) {
        try {
            Optional<TicketInfo> persistObj = ticketRepository.findById(ticketId);
            if (Objects.nonNull(persistObj) && Objects.nonNull(status)) {
                TicketInfo persistEntity = persistObj.get();
                persistEntity.setStatus(status);
                persistEntity.setUpdatedDate(new Date(System.currentTimeMillis()));
                ticketRepository.saveAndFlush(persistEntity);
            }
        }catch (RuntimeException exception) {
            log.error("Exception in cancelTicket() method: "+exception);
            throw new TicketServiceException(exception);
        }
    }
}
