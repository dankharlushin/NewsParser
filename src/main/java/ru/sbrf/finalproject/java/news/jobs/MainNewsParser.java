package ru.sbrf.finalproject.java.news.jobs;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.sbrf.finalproject.java.news.models.News;
import ru.sbrf.finalproject.java.news.services.news.NewsService;
import ru.sbrf.finalproject.java.news.urlcontainer.UrlContainer;

import java.io.IOException;

@Component
public class MainNewsParser {

    private static String mainNewsUrl = "https://rt.rbc.ru/";

    @Autowired
    private NewsService newsService;

    @Autowired
    private UrlContainer urlContainer;

    @Scheduled(fixedDelay = 10000)
    public void parseNewNews() {
        String section = "Главные новости";
        String url = urlContainer.getUrl(section);
        try {
            Document doc = Jsoup.connect(url)
                    .userAgent("Chrome")
                    .timeout(5000)
                    .referrer("https://google.com")//.proxy("proxy.kpfu.ru", 8080)
                    .get();

            Elements news = doc.getElementsByClass("main__feed js-main-reload-item");
            for (Element el : news) {
                String newsTitle = el.getElementsByClass("main__feed__title").first().ownText();
                if (!newsService.isExist(newsTitle)) {
                    News mainNews = new News();
                    mainNews.setTitle(newsTitle);
                    mainNews.setSection(section);
                    mainNews.setUrl(el.getElementsByTag("a").first().attr("href"));
                    newsService.save(mainNews);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
