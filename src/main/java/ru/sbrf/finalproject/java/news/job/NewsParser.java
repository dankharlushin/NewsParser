package ru.sbrf.finalproject.java.news.job;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.sbrf.finalproject.java.news.model.News;
import ru.sbrf.finalproject.java.news.service.NewsService;

import java.io.IOException;

@Component
public class NewsParser {

    @Autowired
    NewsService newsService;

    @Scheduled(fixedDelay = 10000)
    public void parseNewNews() {
        String url = "https://rt.rbc.ru/";

        try {
            Document doc = Jsoup.connect(url)
                    .userAgent("Chrome")
                    .timeout(5000)
                    .referrer("https://google.com").proxy("proxy.kpfu.ru", 8080)
                    .get();

            Elements news = doc.getElementsByClass("main__feed__title");
            for (Element el : news) {
                String newsTitle = el.ownText();
                if (!newsService.isExist(newsTitle)) {
                    News obj = new News();
                    obj.setTitle(newsTitle);
                    newsService.save(obj);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
