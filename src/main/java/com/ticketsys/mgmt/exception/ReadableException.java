package com.ticketsys.mgmt.exception;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageNotReadableException;

public class ReadableException extends HttpMessageNotReadableException {
    private String message;
    public ReadableException(String message, Throwable cause, HttpInputMessage httpInputMessage) {
        super(message, cause, httpInputMessage);
        this.message = message;
        try {
            System.out.println("httpInputMessage: " + httpInputMessage.getBody());
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String getMessage() {
        return this.message;
    }
}
