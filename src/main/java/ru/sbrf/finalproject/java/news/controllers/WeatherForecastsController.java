package ru.sbrf.finalproject.java.news.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.sbrf.finalproject.java.news.dto.WeatherForecastDTO;
import ru.sbrf.finalproject.java.news.exceptions.CityNotFoundException;
import ru.sbrf.finalproject.java.news.exceptions.NotSupportedDateException;
import ru.sbrf.finalproject.java.news.models.WeatherForecast;
import ru.sbrf.finalproject.java.news.services.weather.WeatherForecastService;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@RestController
public class WeatherForecastsController {

    @Autowired
    private WeatherForecastService weatherForecastService;

    @GetMapping("/forecast")
    public WeatherForecastDTO getWeatherForecast(@RequestParam("city") String city,
                                              @RequestParam("date") String date) throws CityNotFoundException, NotSupportedDateException, JsonProcessingException {

        LocalDate localDate = strToLocalDate(date);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        WeatherForecast forecast = weatherForecastService.getForecast(city, localDate);

        WeatherForecastDTO forecastDTO = new WeatherForecastDTO();
        forecastDTO.setDate(forecast.getDate().format(formatter));
        forecastDTO.setCity(forecast.getCity());
        forecastDTO.setTemperature(forecast.getTemperature());
        forecastDTO.setWeather(forecast.getWeather());

        return forecastDTO;
    }

    @GetMapping("/forecastdto")
    public WeatherForecastDTO getWeather(@RequestBody WeatherForecastDTO forecastDTO) throws NotSupportedDateException, CityNotFoundException, JsonProcessingException {

        LocalDate localDate = strToLocalDate(forecastDTO.getDate());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        WeatherForecast forecast = weatherForecastService.getForecast(forecastDTO.getCity(), localDate);

        forecastDTO.setDate(forecast.getDate().format(formatter));
        forecastDTO.setCity(forecast.getCity());
        forecastDTO.setTemperature(forecast.getTemperature());
        forecastDTO.setWeather(forecast.getWeather());

        return forecastDTO;
    }

    public static LocalDate strToLocalDate(String str) throws JsonProcessingException {
        String stringDate = "\"" + str + "\"";

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        return mapper.readValue(stringDate, LocalDate.class);
    }
}
