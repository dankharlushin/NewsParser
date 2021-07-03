package ru.sbrf.finalproject.java.news.jobs;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.sbrf.finalproject.java.news.models.ExchangeRate;
import ru.sbrf.finalproject.java.news.services.rates.ExchangeRateService;
import ru.sbrf.finalproject.java.news.urlcontainer.UrlContainer;

import java.io.IOException;

@Component
public class ExchangeRateParser {

    @Autowired
    private ExchangeRateService exchangeRateService;

    @Autowired
    private UrlContainer urlContainer;

    @Scheduled(fixedDelay = 10000)
    public void parseExchangeRate() {
        String url = urlContainer.getUrl("ratesUrl");
        try {
            Document doc = Jsoup.connect(url)
                    .userAgent("Chrome")
                    .timeout(5000)
                    .referrer("https://google.com")//.proxy("proxy.kpfu.ru", 8080)
                    .get();

            Elements exRates = doc.getElementsByClass("data");
            Elements rows = exRates.select("tr"); //строки

            for (int i = 1; i < rows.size(); i++) {
                Elements cols = rows.get(i).select("td");
                if (cols.get(1).text().equals("USD") | cols.get(1).text().equals("EUR")
                        | cols.get(1).text().equals("CHF") | cols.get(1).text().equals("CNY")) {
                    ExchangeRate exchangeRate = new ExchangeRate();
                    exchangeRate.setCurrency(cols.get(3).text());
                    exchangeRate.setRate(Double.parseDouble(cols.get(4).text().replaceAll(",", ".")));
                    exchangeRateService.update(exchangeRate);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
