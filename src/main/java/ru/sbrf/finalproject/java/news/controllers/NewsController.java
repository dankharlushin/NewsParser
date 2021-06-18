package ru.sbrf.finalproject.java.news.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.sbrf.finalproject.java.news.model.ExchangeRate;
import ru.sbrf.finalproject.java.news.model.News;
import ru.sbrf.finalproject.java.news.service.ExchangeRateService;
import ru.sbrf.finalproject.java.news.service.NewsService;

import java.util.List;

@RestController
public class NewsController {

    @Autowired
    private NewsService newsService;

    @Autowired
    private ExchangeRateService exchangeRateService;

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


}
