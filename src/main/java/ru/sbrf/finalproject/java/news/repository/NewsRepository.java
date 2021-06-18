package ru.sbrf.finalproject.java.news.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.sbrf.finalproject.java.news.model.News;

import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {

    boolean existsNewsByTitle(String title);

    List<News> getNewsBySection(String section);
}
