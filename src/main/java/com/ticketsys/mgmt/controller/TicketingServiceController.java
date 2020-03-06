package com.ticketsys.mgmt.controller;

import com.ticketsys.mgmt.config.TicketInfoValidator;
import com.ticketsys.mgmt.constants.ErrorCode;
import com.ticketsys.mgmt.domain.AppIssue;
import com.ticketsys.mgmt.dto.request.TicketInfoRequest;
import com.ticketsys.mgmt.dto.response.TicketInfoResponse;
import com.ticketsys.mgmt.exception.ServiceError;
import com.ticketsys.mgmt.exception.TicketServiceException;
import com.ticketsys.mgmt.service.JiraService;
import com.ticketsys.mgmt.service.TicketService;
import com.ticketsys.mgmt.util.MapperUtil;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author mdoss
 */
@RestController
@RequestMapping(value = "ticketsysmgmt/", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
@Slf4j
@CrossOrigin(origins="http://localhost:4200", allowedHeaders="*")
@Api(value="Ticket Management System", description="Operations pertaining to ticket in Ticket Management System")
public class TicketingServiceController {

    @Autowired
    @NotNull
    private TicketService ticketService;

    @Autowired
    private JiraService jiraService;

    /**
     * Persist transient ticketInfo domain object.
     *
     * @param requestDto
     * @return ticketInfoResponse entity.
     */
    @ApiOperation(value = "Create new ticket", nickname = "Jira ticket", response = TicketInfoResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ticket successfully created"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource", response = ServiceError.class),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden",  response = ServiceError.class),
            @ApiResponse(code = 404, message = "The resource you were try to reach is not found",  response = ServiceError.class)
    })
    @PostMapping(value = "saveTicket", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TicketInfoResponse> saveTicket(
            @ApiParam(value = "TicketInfo request data transfer object", required = true)
            @RequestBody @Valid TicketInfoRequest requestDto) throws TicketServiceException, Exception {
        TicketInfoResponse responseDto = ticketService.save(requestDto);
        if(Objects.isNull(responseDto)) {
            throw new TicketServiceException(ErrorCode.ERR_BADREQ400.getErrorCode(), ErrorCode.ERR_BADREQ400.getMessage());
        }
        //create ticket in jira
//        AppIssue issue = MapperUtil.getInstance().mapAppIssue(responseDto);
//        jiraService.createIssueInJira(issue);
        return new ResponseEntity(responseDto, HttpStatus.OK);
    }

    /**
     * Load all ticketInfo domain data.
     * @return collection of ticketInfoResponse page.
     */
    @ApiOperation(value = "View a list of tickets", nickname = "Load all ticket", response = TicketInfoResponse.class)
    @GetMapping("loadPage")
    public Page<TicketInfoResponse> loadAll() throws TicketServiceException {
        return ticketService.loadPage(0, 6);
    }

    /**
     * ticketInfo find by ticketId.
     * @param ticketId
     * @return response entity as ticketInfoResponse.
     */
    @ApiOperation(value = "Ticket findBy ticketId", nickname = "findTicketById", response = TicketInfoResponse.class)
    @GetMapping("findBy/{ticketId}")
    public ResponseEntity<TicketInfoResponse> findByTicketId(
            @ApiParam(value = "ticketId must be greater than or equeal to 1", required = true, example = "120")
            @PathVariable("ticketId")
            @Min(value = 1, message = "ticketId must be greater than or equal to 1")
            @Max(value = Integer.MAX_VALUE - 1) Integer ticketId ) throws TicketServiceException, Exception {
        return ResponseEntity.ok(this.ticketService.findById(ticketId));
    }

    /**
     * updateTicket.
     * @param requestDto
     * @return
     * @throws TicketServiceException
     */
    @ApiOperation(value = "Update ticket by ticketId", nickname = "updateTicket", response = TicketInfoResponse.class)
    @PutMapping(value = "updateTicket", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TicketInfoResponse> updateTicket(
            @ApiParam(value = "TicketInfo request data transfer object", required = true)
            @RequestBody @Valid TicketInfoRequest requestDto) throws TicketServiceException {
        TicketInfoResponse responseDto = ticketService.updateTicket(requestDto);
        return ResponseEntity.ok(responseDto);
    }

    /**
     * get ticket's count findBy userId.
     * @param userId
     * @return {<string>count</string>: <long>ticket's count</long> }
     * @throws TicketServiceException
     */
    @ApiOperation(value = "get tickets count find by userId", nickname = "ticketCountByUserId", response = Map.class)
    @GetMapping("getTicketsCountFindBy/{userId}")
    public ResponseEntity<Map<String, Long>> getTicketsCountFindByUserId(
            @ApiParam(value = "UserId not blank size must be between 10 to 50", required = true)
           @PathVariable("userId") @Size(min=5, max = 50, message = "UserId not blank size must be between 10 to 50") String userId)
        throws TicketServiceException {
        long ticketsCount = ticketService.getTicketsCountFindByUserId(userId);
        Map<String, Long> count = new HashMap<>();
        count.put("count", ticketsCount);
        return ResponseEntity.ok(count);
    }

    /**
     *  get ticket's count.
     * @return {<string>count</string>: <long>ticket's count</long> }
     * @throws TicketServiceException
     */
    @ApiOperation(value = "get all active ticket's", nickname = "getTicketsCount", response = Map.class)
    @GetMapping("getTicketsCount")
    public ResponseEntity<Map<String, Long>> getTicketsCount() throws TicketServiceException {
        long ticketsCount = ticketService.getTicketsCount();
        Map<String, Long> count = new HashMap<>();
        count.put("count", ticketsCount);
        return ResponseEntity.ok(count);
    }
    /**
     * initBinder method, For valid request.
     * @param binder
     */
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new TicketInfoValidator());
    }

}
