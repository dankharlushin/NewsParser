package ru.sbrf.finalproject.java.news.services.rates;

import org.springframework.stereotype.Service;
import ru.sbrf.finalproject.java.news.models.ExchangeRate;

import java.util.List;

@Service
public interface ExchangeRateService {

    void update(ExchangeRate rate);
    List<ExchangeRate> getRates();

}
