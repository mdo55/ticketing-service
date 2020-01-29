package com.ticketsys.mgmt.config;

import com.ticketsys.mgmt.impl.TicketServiceImpl;
import com.ticketsys.mgmt.service.TicketService;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TicketingServiceConfiguration {

    public TicketService ticketService() {
        return new TicketServiceImpl();
    }
}
