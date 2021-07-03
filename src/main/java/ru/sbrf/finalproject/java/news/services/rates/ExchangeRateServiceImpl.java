package ru.sbrf.finalproject.java.news.services.rates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sbrf.finalproject.java.news.models.ExchangeRate;
import ru.sbrf.finalproject.java.news.repositories.ExchangeRateRepository;

import java.util.List;

@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {

    @Autowired
    public ExchangeRateRepository exchangeRateRepository;



    @Override
    public void update(ExchangeRate exchangeRate) {
        if (!exchangeRateRepository.existsByCurrency(exchangeRate.getCurrency())) {
            exchangeRateRepository.save(exchangeRate);
        }
        else {
            exchangeRateRepository.getByCurrency(exchangeRate.getCurrency())
                    .setRate(exchangeRate.getRate());
        }
    }

    @Override
    public List<ExchangeRate> getRates() {
        return exchangeRateRepository.findAll();
    }
}
