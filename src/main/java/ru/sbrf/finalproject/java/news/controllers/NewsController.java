package ru.sbrf.finalproject.java.news.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.sbrf.finalproject.java.news.dto.NewsDTO;
import ru.sbrf.finalproject.java.news.exceptions.CityNotFoundException;
import ru.sbrf.finalproject.java.news.models.ExchangeRate;
import ru.sbrf.finalproject.java.news.models.News;
import ru.sbrf.finalproject.java.news.models.WeatherForecast;
import ru.sbrf.finalproject.java.news.services.rates.ExchangeRateService;
import ru.sbrf.finalproject.java.news.services.news.NewsService;
import ru.sbrf.finalproject.java.news.services.weather.WeatherForecastService;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class NewsController {

    @Autowired
    private NewsService newsService;


    @GetMapping(value = "/news")
    public List<NewsDTO> getMainNews() {
        List<News> news = newsService.getNewsFromSection("Главные новости");
        NewsDTO newsDTO = new NewsDTO();
        return newsDTO.newsToDTO(news);
    }

    @GetMapping(value = "/news/{section}")
    public List<NewsDTO> getNewsFromSection(@PathVariable(value = "section") String section) {
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
        NewsDTO newsDTO = new NewsDTO();
        return newsDTO.newsToDTO(news);

    }

}
