package com.ticketsys.mgmt.constants;

/**
 * Bussiness and Technical error code's are defined here.
 * @author mdoss
 */
public enum ErrorCode {
    ERR_1001("1001","Please contact administrator"),
    ERR_1002("1002", "Service unavailable for an hour"),
    ERR_BADREQ400("400", "Invalid Request, Once of request field have invalid data.");

    private final String errorCode;
    private final String message;

    private ErrorCode(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public String getMessage() {
        return this.message;
    }
}
