package ru.sbrf.finalproject.java.news.service.weather;

import org.springframework.stereotype.Service;
import ru.sbrf.finalproject.java.news.model.WeatherForecast;

import java.time.LocalDate;
import java.util.Date;

@Service
public interface WeatherForecastService {

    WeatherForecast getForecast(String city, LocalDate date);
    WeatherForecast updateForecast(WeatherForecast forecast);

}
