package ru.sbrf.finalproject.java.news.urlcontainer;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UrlContainer {

    private final Map<String, String> container;

    public UrlContainer() {
        this.container = new HashMap<>();
        this.container.put("Главные новости", "https://rt.rbc.ru/");
        this.container.put("Политика", "https://ria.ru/politics/");
        this.container.put("Экономика", "https://ria.ru/economy/");
        this.container.put("Общество", "https://ria.ru/society/");
        this.container.put("Происшествия", "https://ria.ru/incidents/");
        this.container.put("ratesUrl", "https://www.cbr.ru/currency_base/daily/");
        this.container.put("forecastUrl", "https://yandex.ru/pogoda/");
    }

    public String getUrl(String section) {
        return container.get(section);
    }

}
