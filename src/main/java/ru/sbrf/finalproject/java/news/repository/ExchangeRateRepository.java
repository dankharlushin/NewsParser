package ru.sbrf.finalproject.java.news.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.sbrf.finalproject.java.news.model.ExchangeRate;

import java.util.Currency;
import java.util.List;

@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {

    boolean existsByCurrency(String currency);
    ExchangeRate getByCurrency(String currency);
}
