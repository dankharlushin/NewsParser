package ru.sbrf.finalproject.java.news.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sbrf.finalproject.java.news.models.ExchangeRate;

@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {

    boolean existsByCurrency(String currency);
    ExchangeRate getByCurrency(String currency);
}
