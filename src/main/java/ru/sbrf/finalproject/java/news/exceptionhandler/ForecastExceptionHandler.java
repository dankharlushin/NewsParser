package ru.sbrf.finalproject.java.news.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.sbrf.finalproject.java.news.exceptions.CityNotFoundException;
import ru.sbrf.finalproject.java.news.exceptions.NotSupportedDateException;

import java.time.DateTimeException;

@ControllerAdvice
public class ForecastExceptionHandler extends ResponseEntityExceptionHandler {

    @Data
    @AllArgsConstructor
    private static class Message {
        private String message;
    }

    @ExceptionHandler(CityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityCityNotFoundEx() {
        return new ResponseEntity<>(
                new Message("There is no such city"),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({NotSupportedDateException.class, DateTimeException.class})
    protected ResponseEntity<Object> handleEntityNotSupportedDateEx() {
        return new ResponseEntity<>(
                new Message("This date is not supported"),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
