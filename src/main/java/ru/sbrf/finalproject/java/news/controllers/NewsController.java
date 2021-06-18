package ru.sbrf.finalproject.java.news.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public List<News> getAllNews() {
        return newsService.getAllNews();
    }

    @GetMapping(value = "/rates")
    public List<ExchangeRate> getRates() {
        return exchangeRateService.getRates();
    }


}
