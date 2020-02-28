package com.ticketsys.mgmt.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.ticketsys.mgmt.constants.ErrorCode;
import com.ticketsys.mgmt.util.ServiceUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.Arrays;
import java.util.stream.Collectors;

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
    public ResponseEntity<ServiceError> handleUnExpectedException(Exception throwable, WebRequest request) {
        TicketServiceException serviceException = new TicketServiceException(throwable);
        return ResponseEntity.ok(serviceException.getServiceError());
    }
    @ExceptionHandler({ConstraintViolationException.class})
    @ResponseStatus
    public ResponseEntity<ServiceError> handleConstraintViolationException(ConstraintViolationException cvException,  WebRequest request) {
//        System.out.println("ConstraintViolationException------"+ cvException.getMessage()+"\n" + request.getDescription(false));
        TicketServiceException serviceException = new TicketServiceException(ErrorCode.ERR_1003, ServiceUtil.segregateMessage(cvException.getMessage()));
        return ResponseEntity.ok(serviceException.getServiceError());
    }
//    @ExceptionHandler({InvalidFormatException.class})
//    @ResponseStatus
//    public ResponseEntity<ServiceError> httpMethodReadableException(InvalidFormatException readableException, WebRequest request) {
//        System.out.println("handleMethodArgumentNotValidException------"+ readableException.getMessage()+"\n" + request.getDescription(false));
//        TicketServiceException serviceException = new TicketServiceException(ErrorCode.ERR_1003, ServiceUtil.segregateMessage(readableException.getMessage()));
//        return ResponseEntity.ok(serviceException.getServiceError());
//    }


}
