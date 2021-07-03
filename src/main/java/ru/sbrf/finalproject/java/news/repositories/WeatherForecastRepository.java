package ru.sbrf.finalproject.java.news.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sbrf.finalproject.java.news.models.WeatherForecast;

import java.time.LocalDate;

@Repository
public interface WeatherForecastRepository extends JpaRepository<WeatherForecast, Long> {

    boolean existsByCityAndDate(String city, LocalDate date);
    WeatherForecast getWeatherForecastsByCityAndDate(String city, LocalDate date);
}
