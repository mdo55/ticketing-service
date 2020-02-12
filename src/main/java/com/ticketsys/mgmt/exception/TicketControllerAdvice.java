package com.ticketsys.mgmt.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
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
    @ExceptionHandler({Exception.class})
    @ResponseStatus
    public ResponseEntity<ServiceError> handleUnExpectedException(Exception throwable, WebRequest request) {
        System.out.println(request.getDescription(false));
        TicketServiceException serviceException = new TicketServiceException(throwable);
        return ResponseEntity.ok(serviceException.getServiceError());
    }
//    @ExceptionHandler({ValidationException.class})
//    @ResponseStatus
//    public ResponseEntity<ServiceError> handleMethodArgumentNotValidException(ValidationException vException, WebRequest request) {
//        log.info("handleMethodArgumentNotValidException------"+ vException.getMessage()+"\n" + request.getDescription(false));
//        TicketServiceException serviceException = new TicketServiceException(vException);
//        return ResponseEntity.ok(serviceException.getServiceError());
//    }
}
