package com.ticketsys.mgmt.filter;

//import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.node.JsonNodeCreator;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ticketsys.mgmt.config.JsonSchemaManager;
import com.ticketsys.mgmt.constants.ErrorCode;
import com.ticketsys.mgmt.dto.request.TicketInfoRequest;
import com.ticketsys.mgmt.exception.TicketServiceException;
import com.ticketsys.mgmt.util.ServiceUtil;
import org.apache.commons.io.IOUtils;
//import org.codehaus.jackson.JsonNode;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.validation.ConstraintViolationException;
import java.io.IOException;

public class ValidationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        System.out.println("request URI: "+request.getRequestURI());
        String requestBody = "";
        if(request.getRequestURI().contains("ticketsysmgmt")) {
            final WrappedRequest wrapperRequest = new WrappedRequest(request);
            requestBody = wrapperRequest.getBody();
            servletRequest = wrapperRequest;
        }
        System.out.println("reqeustBody: "+requestBody);
        if(!StringUtils.isEmpty(requestBody)) {
//            try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            ObjectReader reader = mapper.readerFor(TicketInfoRequest.class);
            TicketInfoRequest req = reader.with(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL).readValue(requestBody);
//                JsonNode node = mapper.readTree(mapper.writeValueAsString(req));
//                System.out.println("node: "+node);

//            } catch (InvalidFormatException ex) {
//                System.out.println(ex.getValue() +"-------"+ ex.getMessage() +"------------");
//            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
