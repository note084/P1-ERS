package com.revature.exceptions;

public class ReimbursementDoesNotExist extends RuntimeException{
    public ReimbursementDoesNotExist() {
    }

    public ReimbursementDoesNotExist(String message) {
        super(message);
    }

    public ReimbursementDoesNotExist(String message, Throwable cause) {
        super(message, cause);
    }

    public ReimbursementDoesNotExist(Throwable cause) {
        super(cause);
    }

    public ReimbursementDoesNotExist(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
