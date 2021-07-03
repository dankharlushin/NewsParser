package ru.sbrf.finalproject.java.news.dto;

import com.sun.istack.NotNull;
import lombok.Data;
import ru.sbrf.finalproject.java.news.models.ExchangeRate;
import ru.sbrf.finalproject.java.news.models.News;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Data
public class ExchangeRatesDTO {


    @NotNull
    private String currency;

    @NotNull
    private Double rate;

    public List<ExchangeRatesDTO> toDTO(List<ExchangeRate> rates) {
        List<ExchangeRatesDTO> ratesDTO = new ArrayList<>();
        for (ExchangeRate er : rates) {
            ExchangeRatesDTO erDTO = new ExchangeRatesDTO();
            erDTO.setCurrency(er.getCurrency());
            erDTO.setRate(er.getRate());
            ratesDTO.add(erDTO);
        }

        return ratesDTO;
    }
}
