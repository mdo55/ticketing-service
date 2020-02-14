package com.ticketsys.mgmt.exception;

import com.ticketsys.mgmt.constants.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
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
    public ResponseEntity<ServiceError> handleMethodArgumentNotValidException(ConstraintViolationException cvException, WebRequest request) {
        System.out.println("handleMethodArgumentNotValidException------"+ cvException.getMessage()+"\n" + request.getDescription(false));
        TicketServiceException serviceException = new TicketServiceException(ErrorCode.ERR_1003, segregateMessage(cvException.getMessage()));
        return ResponseEntity.ok(serviceException.getServiceError());
    }

    /**
     * segregate constraintViolation exception.
     * @param kvPairMessage
     * @return string[]
     */
    public Object segregateMessage(String kvPairMessage) {
        if (!StringUtils.isEmpty(kvPairMessage)) {
            return Arrays.asList(
                    kvPairMessage.split("\\,")).stream()
                    .map(kvMsg -> kvMsg.split("\\:").length > 1
                            ? kvMsg.split("\\:")[1]
                            : kvMsg.split("\\:")[0])
                    .collect(Collectors.joining(","));
//            return msgList.toArray();
        }else {
            return new Object();
        }
    }
}
