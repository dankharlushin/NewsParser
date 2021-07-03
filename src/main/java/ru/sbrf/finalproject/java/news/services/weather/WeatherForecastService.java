package ru.sbrf.finalproject.java.news.services.weather;

import org.springframework.stereotype.Service;
import ru.sbrf.finalproject.java.news.exceptions.CityNotFoundException;
import ru.sbrf.finalproject.java.news.exceptions.NotSupportedDateException;
import ru.sbrf.finalproject.java.news.models.WeatherForecast;

import java.time.LocalDate;

@Service
public interface WeatherForecastService {

    WeatherForecast getForecast(String city, LocalDate date) throws CityNotFoundException, NotSupportedDateException;
    WeatherForecast updateForecast(WeatherForecast forecast);

}
