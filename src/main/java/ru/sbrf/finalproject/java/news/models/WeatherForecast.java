package ru.sbrf.finalproject.java.news.models;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.sbrf.finalproject.java.news.exceptions.NotSupportedDateException;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WeatherForecast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String city;

    @NotNull
    private LocalDate date;

    private String temperature;

    private String weather;

    public void setDate(LocalDate date) throws NotSupportedDateException {
        if (!date.isAfter(LocalDate.now().plusDays(9)) &&
        !date.isBefore(LocalDate.now())) {
            this.date = date;
        }
        else {
            throw new NotSupportedDateException("Not supported date");
        }
    }
}
