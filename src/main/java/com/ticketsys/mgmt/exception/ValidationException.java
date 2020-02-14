package com.ticketsys.mgmt.exception;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

public class ValidationException extends ConstraintViolationException {

    private String message;
    public ValidationException(String message, Set<? extends ConstraintViolation<?>> constraintViolations) {
        super(message, constraintViolations);
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
