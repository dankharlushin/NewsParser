package ru.sbrf.finalproject.java.news.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.sbrf.finalproject.java.news.exceptions.CityNotFoundException;
import ru.sbrf.finalproject.java.news.models.WeatherForecast;
import ru.sbrf.finalproject.java.news.services.weather.WeatherForecastService;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class WeatherForecastsController {

    @Autowired
    private WeatherForecastService weatherForecastService;

    @GetMapping(value = "/forecast")
    public WeatherForecast getWeatherForecast(@RequestParam("city") String city,
                                              @RequestParam("date") String date) throws CityNotFoundException {
        List<Integer> listofDate = Arrays.stream(date
                .split("-"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        LocalDate localDate = LocalDate.of(listofDate.get(2), listofDate.get(1), listofDate.get(0));
        return weatherForecastService.getForecast(city, localDate);
    }
}
