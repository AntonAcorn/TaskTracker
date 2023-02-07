package ru.acorn.taskTracker.exception;

public class ProjectNotFoundException extends RuntimeException {
    public ProjectNotFoundException() {
        super();
    }

    public ProjectNotFoundException(String message) {
        super(message);
    }

    public ProjectNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProjectNotFoundException(Throwable cause) {
        super(cause);
    }

    protected ProjectNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
