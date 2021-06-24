package ru.sbrf.finalproject.java.news.services.weather;

import org.springframework.stereotype.Service;
import ru.sbrf.finalproject.java.news.models.WeatherForecast;

import java.time.LocalDate;

@Service
public interface WeatherForecastService {

    WeatherForecast getForecast(String city, LocalDate date);
    WeatherForecast updateForecast(WeatherForecast forecast);

}
