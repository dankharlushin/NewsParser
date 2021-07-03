package ru.sbrf.finalproject.java.news.dto;


import lombok.Data;
import ru.sbrf.finalproject.java.news.models.WeatherForecast;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Data
public class WeatherForecastDTO {

    @NotNull
    private String city;
    @NotNull
    private String date;

    private String temperature;

    private String weather;

    public List<WeatherForecastDTO> toDTO(List<WeatherForecast> forecasts) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        List<WeatherForecastDTO> forecastDTO = new ArrayList<>();
        for (WeatherForecast wf : forecasts) {
            WeatherForecastDTO wfDTO = new WeatherForecastDTO();
            wfDTO.setCity(wf.getCity());
            wfDTO.setDate(wf.getDate().format(formatter));
            wfDTO.setTemperature(wf.getTemperature());
            wfDTO.setWeather(wf.getWeather());
            forecastDTO.add(wfDTO);
        }

        return forecastDTO;
    }

}
