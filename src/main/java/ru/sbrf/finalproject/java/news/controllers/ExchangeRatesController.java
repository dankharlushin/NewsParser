package ru.sbrf.finalproject.java.news.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sbrf.finalproject.java.news.dto.ExchangeRatesDTO;
import ru.sbrf.finalproject.java.news.models.ExchangeRate;
import ru.sbrf.finalproject.java.news.services.rates.ExchangeRateService;

import java.util.List;

@RestController
public class ExchangeRatesController {

    @Autowired
    private ExchangeRateService exchangeRateService;

    @GetMapping(value = "/rates")
    public List<ExchangeRatesDTO> getRates() {
        ExchangeRatesDTO erDTO = new ExchangeRatesDTO();
        return erDTO.toDTO(exchangeRateService.getRates());
    }
}
