package ru.sbrf.finalproject.java.news.job;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.sbrf.finalproject.java.news.model.News;
import ru.sbrf.finalproject.java.news.service.news.NewsService;

import java.io.IOException;

@Component
public class MainNewsParser {

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

            Elements news = doc.getElementsByClass("main__feed js-main-reload-item");
            for (Element el : news) {
                String newsTitle = el.getElementsByClass("main__feed__title").first().ownText();
                if (!newsService.isExist(newsTitle)) {
                    News mainNews = new News();
                    mainNews.setTitle(newsTitle);
                    mainNews.setSection("Главное");
                    mainNews.setUrl(el.getElementsByTag("a").first().attr("href"));
                    newsService.save(mainNews);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
