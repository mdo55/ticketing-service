package com.ticketsys.mgmt.config;


import com.ticketsys.mgmt.dto.request.TicketInfoRequest;
import com.ticketsys.mgmt.exception.TicketServiceException;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;
import java.util.stream.Collectors;

/**
 * ticketInfo validator.
 * @author mdoss.
 */
public class TicketInfoValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return TicketInfoRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        TicketInfoRequest request = (TicketInfoRequest) target;
        if(errors.hasFieldErrors())
        {
            List<String> listOfErrors= errors.getAllErrors().stream().map(error -> error.getDefaultMessage()).collect(Collectors.toList());
//            System.out.println("%%%%%^^^^"+errors.getAllErrors().stream().map(error -> error.getDefaultMessage()).collect(Collectors.toList()));
            throw new TicketServiceException(errors.getFieldError().getField(), listOfErrors.toString());
        }
    }
}
