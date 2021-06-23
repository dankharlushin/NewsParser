package ru.sbrf.finalproject.java.news.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sbrf.finalproject.java.news.model.WeatherForecast;

import java.time.LocalDate;
import java.util.Date;

@Repository
public interface WeatherForecastRepository extends JpaRepository<WeatherForecast, Long> {

    boolean existsByCityAndDate(String city, LocalDate date);
    WeatherForecast getWeatherForecastsByCityAndDate(String city, LocalDate date);
}
