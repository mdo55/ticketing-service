package com.ticketsys.mgmt.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ticketsys.mgmt.constants.ErrorCode;
import lombok.Getter;

import java.text.MessageFormat;
import java.time.LocalDateTime;

/**
 * @author mdoss
 */
@Getter
public class ServiceError {

    private String status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private String message;
    private String debugMessage;

    /**
     * empty constructor.
     */
    public ServiceError() {
        this.timestamp = LocalDateTime.now();
    }

    /**
     * argument constructor.
     * @param status
     * @param message
     */
    public ServiceError(String status, String message) {
        this();
        this.status = status;
        this.message = message;
    }

    /**
     * argument constructor.
     * @param status
     * @param message
     * @param debugMessage
     */
    public ServiceError(String status, String message, String debugMessage) {
        this();
        this.status = status;
        this.message = message;
        this.debugMessage = debugMessage;
    }

    /**
     * argument constructor.
     * @param throwable
     */
    public ServiceError(Throwable throwable) {
        this();
        this.status = ErrorCode.ERR_1001.getErrorCode();
        this.message = "Unexpected error";
        this.debugMessage = throwable.getLocalizedMessage();
    }

    /**
     * argument cons
     * @param errorCode
     * @param arg
     */
    public ServiceError(ErrorCode errorCode, Object ... arg) {
        this();
        MessageFormat messageFormat = new MessageFormat(errorCode.getMessage());
        this.status = errorCode.getErrorCode();
        this.message = messageFormat.format(arg);
    }
}
