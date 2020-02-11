package com.ticketsys.mgmt.config;

import com.ticketsys.mgmt.impl.TicketServiceImpl;
import com.ticketsys.mgmt.service.TicketService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

/**
 * ticketing-service configuration class.
 *
 * @author mdoss .
 */
@Configuration
public class TicketingServiceConfiguration {

    public TicketService ticketService() {
        return new TicketServiceImpl();
    }

    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        return new MethodValidationPostProcessor();
    }
}
