package ru.sbrf.finalproject.java.news.dto;

import com.sun.istack.NotNull;
import lombok.Data;
import ru.sbrf.finalproject.java.news.models.News;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Data
public class NewsDTO {


    private String title;

    private String section;

    private String url;

    public List<NewsDTO> newsToDTO(List<News> news) {
        List<NewsDTO> newsDTO = new ArrayList<>();
        for (News n : news) {
            NewsDTO nDTO = new NewsDTO();
            nDTO.setTitle(n.getTitle());
            nDTO.setSection(n.getSection());
            nDTO.setUrl(n.getUrl());
            newsDTO.add(nDTO);
        }

        return newsDTO;
    }
}
