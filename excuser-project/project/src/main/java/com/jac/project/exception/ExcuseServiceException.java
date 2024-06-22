package com.jac.project.exception;

public class ExcuseServiceException extends RuntimeException {
    public ExcuseServiceException(String message) {
        super(message);
    }

    public ExcuseServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
