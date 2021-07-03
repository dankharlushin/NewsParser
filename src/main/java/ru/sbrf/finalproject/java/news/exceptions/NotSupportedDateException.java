package ru.sbrf.finalproject.java.news.exceptions;

public class NotSupportedDateException extends Exception {

    public NotSupportedDateException() {
    }

    public NotSupportedDateException(String message) {
        super(message);
    }

    public NotSupportedDateException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotSupportedDateException(Throwable cause) {
        super(cause);
    }

    public NotSupportedDateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
