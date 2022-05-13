package com.revature.exceptions;

public class UserPasswordDoesNotMatchException extends RuntimeException{
    public UserPasswordDoesNotMatchException() {
    }

    public UserPasswordDoesNotMatchException(String message) {
        super(message);
    }

    public UserPasswordDoesNotMatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserPasswordDoesNotMatchException(Throwable cause) {
        super(cause);
    }

    public UserPasswordDoesNotMatchException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
