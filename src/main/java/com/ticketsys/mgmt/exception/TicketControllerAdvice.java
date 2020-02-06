package com.ticketsys.mgmt.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author mdoss
 */
@ControllerAdvice(annotations = RestController.class)
public class TicketControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(TicketServiceException.class)
    public ResponseEntity<ServiceError> handleServiceException( TicketServiceException exception ) {
        TicketServiceException serviceException = (TicketServiceException) exception;
        return ResponseEntity.ok(serviceException.getServiceError());
    }
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ServiceError> handleUnExpectedException(Throwable throwable) {
        TicketServiceException serviceException = new TicketServiceException(throwable);
        return ResponseEntity.ok(serviceException.getServiceError());
    }
}
