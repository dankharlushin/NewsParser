package ru.sbrf.finalproject.java.news.controllers;

import org.apache.el.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.sbrf.finalproject.java.news.model.ExchangeRate;
import ru.sbrf.finalproject.java.news.model.News;
import ru.sbrf.finalproject.java.news.model.WeatherForecast;
import ru.sbrf.finalproject.java.news.service.rates.ExchangeRateService;
import ru.sbrf.finalproject.java.news.service.news.NewsService;
import ru.sbrf.finalproject.java.news.service.weather.WeatherForecastService;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class NewsController {

    @Autowired
    private NewsService newsService;

    @Autowired
    private ExchangeRateService exchangeRateService;

    @Autowired
    private WeatherForecastService weatherForecastService;

    @GetMapping(value = "/news")
    public List<News> getMainNews() {
        return newsService.getNewsFromSection("Главное");
    }

    @GetMapping(value = "/news/{section}")
    public List<News> getNewsFromSection(@PathVariable(value = "section") String section) {
        List<News> news;
        switch (section) {
            case "politics":
                news = newsService.getNewsFromSection("Политика");
                break;
            case "economy":
                news = newsService.getNewsFromSection("Экономика");
                break;
            case "society":
                news = newsService.getNewsFromSection("Общество");
                break;
            case "incidents":
                news = newsService.getNewsFromSection("Происшествия");
                break;
            default:
                news = null;
        }

        return news;

    }


    @GetMapping(value = "/rates")
    public List<ExchangeRate> getRates() {
        return exchangeRateService.getRates();
    }

    @GetMapping(value = "/forecast")
    public WeatherForecast getWeatherForecast(@RequestParam("city") String city,
                                              @RequestParam("date") String date) {
        List<Integer> listofDate = Arrays.stream(date
                .split("-"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        LocalDate localDate = LocalDate.of(listofDate.get(2), listofDate.get(1), listofDate.get(0));
        return weatherForecastService.getForecast(city, localDate);
    }

}
