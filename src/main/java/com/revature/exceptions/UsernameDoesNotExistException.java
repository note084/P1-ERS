package com.revature.exceptions;

public class UsernameDoesNotExistException extends RuntimeException {
    public UsernameDoesNotExistException() {
    }

    public UsernameDoesNotExistException(String message) {
        super(message);
    }

    public UsernameDoesNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public UsernameDoesNotExistException(Throwable cause) {
        super(cause);
    }

    public UsernameDoesNotExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
