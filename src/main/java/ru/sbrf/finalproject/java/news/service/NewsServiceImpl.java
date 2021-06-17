package ru.sbrf.finalproject.java.news.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sbrf.finalproject.java.news.model.News;
import ru.sbrf.finalproject.java.news.repository.NewsRepository;

import java.util.List;

@Service
public class NewsServiceImpl  implements NewsService {

    @Autowired
    private NewsRepository repository;

    @Override
    public void save(News news) {
        repository.save(news);
    }

    @Override
    public boolean isExist(String newsTitle) {
        return repository.existsNewsByTitle(newsTitle);
    }

    @Override
    public List<News> getAllNews() {
        return repository.findAll();
    }
}
