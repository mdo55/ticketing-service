package com.ticketsys.mgmt.exception;

import com.ticketsys.mgmt.constants.ErrorCode;
import lombok.Getter;

import java.text.MessageFormat;

/**
 * Ticket tracking system service exception.
 * @author mdoss
 */
@Getter
public class TicketServiceException extends RuntimeException {
    private ServiceError serviceError;

    /**
     * argument constructor.
     * @param status
     * @param message
     */
    public TicketServiceException(String status, String message) {
        serviceError = new ServiceError(status, message);
    }

    /**
     * argument constructor.
     * @param status
     * @param message
     * @param debugMessage
     */
    public TicketServiceException(String status, String message, String debugMessage) {
        serviceError = new ServiceError(status, message, debugMessage);
    }

    /**
     * argument constructor.
     * @param throwable
     */
    public TicketServiceException(Throwable throwable) {
        serviceError = new ServiceError(throwable);
    }

    /**
     * argument constructor.
     * @param errorCode
     * @param arg
     */
    public TicketServiceException(ErrorCode errorCode, Object ... arg) {
        serviceError = new ServiceError(errorCode, arg);
    }
}
