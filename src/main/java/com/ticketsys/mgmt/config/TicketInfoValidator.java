package com.ticketsys.mgmt.config;


import com.ticketsys.mgmt.dto.request.TicketInfoRequest;
import com.ticketsys.mgmt.exception.TicketServiceException;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

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
//        ValidationUtils.rejectIfEmpty(errors, "ticket", "ticket.empty");
//        ValidationUtils.rejectIfEmpty(errors, "description", "description.empty");
        TicketInfoRequest request = (TicketInfoRequest) target;
        if(errors.hasFieldErrors())
        {
            System.out.println(errors.getFieldError());
            throw new TicketServiceException(errors.getFieldError().getField(), errors.getFieldError().getRejectedValue().toString());
        }
    }
}
