package com.ticketsys.mgmt.controller;

import com.ticketsys.mgmt.dto.request.TicketInfoRequest;
import com.ticketsys.mgmt.dto.response.TicketInfoResponse;
import com.ticketsys.mgmt.service.TicketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("ticketsysmgmt/")
@Slf4j
public class TicketingServiceController {

    @Autowired
    @NotNull
    private TicketService ticketService;

    @PostMapping(value = "saveTicket", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<TicketInfoResponse> saveTicket(@RequestBody @Valid TicketInfoRequest requestDto,
//           @RequestParam(value = "clipFile") MultipartFile file) {
    public ResponseEntity<TicketInfoResponse> saveTicket(@RequestBody @Valid TicketInfoRequest requestDto) {
        log.info("method call...."+requestDto);
        TicketInfoResponse responseDto = ticketService.save(requestDto);
        return new ResponseEntity(responseDto, HttpStatus.OK);
    }
}
