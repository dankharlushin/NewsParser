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
import java.util.Arrays;
import java.util.List;

@Component
public class SectionNewsParser {

    public static final String politicSectionUrl = "https://ria.ru/politics/";
    public static final String economicSectionUrl = "https://ria.ru/economy/";
    public static final String societySectionUrl = "https://ria.ru/society/";
    public static final String incidentsSectionUrl = "https://ria.ru/incidents/";


    @Autowired
    NewsService newsService;

    @Autowired
    UrlContainer urlContainer;


    @Scheduled(fixedDelay = 10000)
    public void parseNewSectionNews() {
        List<String> urls = Arrays.asList(politicSectionUrl,
                economicSectionUrl,
                societySectionUrl,
                incidentsSectionUrl);

        for (String url: urls) {
            String section = SectionNewsParser.getSection(url);
            try {
                Document doc = Jsoup.connect(url)
                        .userAgent("Chrome")
                        .timeout(5000)
                        .referrer("https://google.com").proxy("proxy.kpfu.ru", 8080)
                        .get();

                Elements news = doc.getElementsByClass("list-item");
                for (Element el: news) {
                    String newsTitle = el.getElementsByClass("list-item__title color-font-hover-only")
                            .first()
                            .ownText();
                    if (!newsService.isExist(newsTitle)) {
                        News sectionNews = new News();
                        sectionNews.setTitle(newsTitle);
                        sectionNews.setSection(section);
                        sectionNews.setUrl(el.getElementsByTag("a").first().attr("href"));
                        newsService.save(sectionNews);
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getSection(String url) {
        String section = null;
        switch (url) {
            case politicSectionUrl:
                section = "Политика";
                break;
            case economicSectionUrl:
                section = "Экономика";
                break;
            case societySectionUrl:
                section = "Общество";
                break;
            case incidentsSectionUrl:
                section = "Происшествия";
                break;
        }

        return section;
    }

}