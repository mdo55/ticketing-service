package com.ticketsys.mgmt.controller;

import com.ticketsys.mgmt.dto.request.TicketInfoRequest;
import com.ticketsys.mgmt.dto.response.TicketInfoResponse;
import com.ticketsys.mgmt.service.TicketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author mdoss
 */
@RestController
@RequestMapping("ticketsysmgmt/")
@Slf4j
@CrossOrigin(origins="http://localhost:4200", allowedHeaders="*")
public class TicketingServiceController {

    @Autowired
    @NotNull
    private TicketService ticketService;

    /**
     * Persist transient ticketInfo domain object.
     * @param requestDto
     * @return ticketInfoResponse entity.
     */
    @PostMapping(value = "saveTicket", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<TicketInfoResponse> saveTicket(@RequestBody @Valid TicketInfoRequest requestDto,
//           @RequestParam(value = "clipFile") MultipartFile file) {
    public ResponseEntity<TicketInfoResponse> saveTicket(@RequestBody @Valid TicketInfoRequest requestDto) {
        TicketInfoResponse responseDto = ticketService.save(requestDto);
        return new ResponseEntity(responseDto, HttpStatus.OK);
    }

    /**
     * Load all ticketInfo domain data.
     * @return collection of ticketInfoResponse page.
     */
    @GetMapping("loadPage")
    public Page<TicketInfoResponse> loadAll() {
        return ticketService.loadPage(0, 6);
    }
}
