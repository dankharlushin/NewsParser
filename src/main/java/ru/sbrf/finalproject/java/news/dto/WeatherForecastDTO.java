package ru.sbrf.finalproject.java.news.dto;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
public class WeatherForecastDTO {

    private Long id;

    private String city;

    private LocalDate date;

    private String temperature;

    private String weather;
}
