package ru.acorn.taskTracker.exception;

public class AppEntityNotFoundException extends RuntimeException {
    public AppEntityNotFoundException() {
        super();
    }

    public AppEntityNotFoundException(String message) {
        super(message);
    }

    public AppEntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public AppEntityNotFoundException(Throwable cause) {
        super(cause);
    }

    protected AppEntityNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
