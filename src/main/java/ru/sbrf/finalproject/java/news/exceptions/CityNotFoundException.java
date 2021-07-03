package ru.sbrf.finalproject.java.news.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "There is no such city")
public class CityNotFoundException extends Exception {
    public CityNotFoundException() {
    }

    public CityNotFoundException(String message) {
        super(message);
    }

    public CityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CityNotFoundException(Throwable cause) {
        super(cause);
    }

    public CityNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
