package org.cambala.exception;

public class ApplicationException extends RuntimeException {

    public final boolean hideStackTrace;

    public final int statusCode;

    public ApplicationException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
        this.hideStackTrace = true;
    }

    public ApplicationException(String message, int statusCode, boolean hideStackTrace) {
        super(message);
        this.statusCode = statusCode;
        this.hideStackTrace = hideStackTrace;
    }
}
