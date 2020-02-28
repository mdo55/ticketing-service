package com.ticketsys.mgmt.config;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.JiraRestClientFactory;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
import com.ticketsys.mgmt.filter.ValidationFilter;
import com.ticketsys.mgmt.impl.TicketServiceImpl;
import com.ticketsys.mgmt.service.TicketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import java.net.URI;

/**
 * ticketing-service configuration class.
 *
 * @author mdoss.
 */
@Configuration
@Slf4j
public class TicketingServiceConfiguration {

    public TicketService ticketService() {
        return new TicketServiceImpl();
    }

    @Bean
    public JiraRestClient jiraRestClient() {
        JiraRestClientFactory factory = new AsynchronousJiraRestClientFactory();
        try{
//            URI uri = new URI("https://mdoss.atlassian.net");
            final URI jiraServerUri = new URI("https://mdoss.atlassian.net/projects/TIC/issues/TIC-1");
            return factory.createWithBasicHttpAuthentication(jiraServerUri, "mdoss@altimetrik.com", "mdoss@altimetrik.com");
        }catch (Exception ex) {
            log.error("Exceptin in jiraRestClient initialization exception: "+ex);
        }
        return null;
    }

    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        return new MethodValidationPostProcessor();
    }

    @Bean
    public FilterRegistrationBean<ValidationFilter> loggingFilter(){
        FilterRegistrationBean<ValidationFilter> registrationBean
                = new FilterRegistrationBean<>();
        registrationBean.setFilter(new ValidationFilter());
        registrationBean.addUrlPatterns("/ticketsysmgmt/*");

        return registrationBean;
    }
}
