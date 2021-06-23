package ru.sbrf.finalproject.java.news.service.news;

import org.springframework.stereotype.Service;
import ru.sbrf.finalproject.java.news.model.News;

import java.util.List;

@Service
public interface NewsService {
    void save(News news);
    boolean isExist(String newsTitle);
    List<News> getAllNews();
    List<News> getNewsFromSection(String section);

}