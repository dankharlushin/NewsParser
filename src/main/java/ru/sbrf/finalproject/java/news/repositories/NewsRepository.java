package ru.sbrf.finalproject.java.news.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sbrf.finalproject.java.news.models.News;

import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {

    boolean existsNewsByTitle(String title);

    List<News> getNewsBySection(String section);
}
