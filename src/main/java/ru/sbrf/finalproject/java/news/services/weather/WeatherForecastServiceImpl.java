package ru.sbrf.finalproject.java.news.services.weather;

import com.ibm.icu.text.Transliterator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sbrf.finalproject.java.news.models.WeatherForecast;
import ru.sbrf.finalproject.java.news.repositories.WeatherForecastRepository;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Locale;

@Service
public class WeatherForecastServiceImpl implements WeatherForecastService {

    //public static final String moscowUrl =
    public static final String weatherUrl = "https://yandex.ru/pogoda/";

    @Autowired
    WeatherForecastRepository weatherForecastRepository;


    @Override
    public WeatherForecast getForecast(String city, LocalDate date) {
        String url = WeatherForecastServiceImpl.getUrlForCity(city);
        WeatherForecast forecast = new WeatherForecast();
        forecast.setCity(city);
        forecast.setDate(date);
        try {
            Document doc = Jsoup.connect(url)
                    .userAgent("Chrome")
                    .timeout(5000)
                    .referrer("https://google.com").proxy("proxy.kpfu.ru", 8080)
                    .get();
            Elements elements = doc.getElementsByClass("card");
            for (Element el: elements) {
                if (el.getElementsByAttribute("data-anchor").size() != 0) {
                    String dataAnchor = el.getElementsByAttribute("data-anchor")
                            .first()
                            .getElementsByClass("forecast-details__day-number")
                            .first()
                            .ownText();
                    if (dataAnchor.equals(String.valueOf(date.getDayOfMonth()))) {
                        Element table = el.getElementsByClass("weather-table__body").first();
                        Element row = table.getElementsByClass("weather-table__row").get(1);
                        forecast.setTemperature(row
                                .getElementsByClass("temp__value temp__value_with-unit")
                                .first()
                                .ownText());
                        forecast.setWeather(row
                                .getElementsByClass("weather-table__body-cell weather-table__body-cell_type_condition")
                                .first()
                                .ownText());
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return updateForecast(forecast);
    }

    @Override
    public WeatherForecast updateForecast(WeatherForecast forecast) {
        if (!weatherForecastRepository.existsByCityAndDate(forecast.getCity(), forecast.getDate())) {
            weatherForecastRepository.save(forecast);
            return forecast;
        }
        else {
            return weatherForecastRepository.getWeatherForecastsByCityAndDate(forecast.getCity(), forecast.getDate());
        }

    }

    public static String getUrlForCity(String city) {
        String url;
        switch (city.toUpperCase(Locale.ROOT)) {
            case "МОСКВА":
                url = WeatherForecastServiceImpl.weatherUrl + "moscow/details";
                break;
            case "САНКТ-ПЕТЕРБУРГ":
                url = WeatherForecastServiceImpl.weatherUrl + "saint-petersburg/details";
                break;
            default:
                var CYRILLIC_TO_LATIN = "Russian-Latin/BGN";
                Transliterator toLatinTrans = Transliterator.getInstance(CYRILLIC_TO_LATIN);
                String result = transliterate(city);
                url = WeatherForecastServiceImpl.weatherUrl + result.toLowerCase(Locale.ROOT) + "/details"; //add translit
                break;
        }

        return url;
    }

    public static String transliterate(String message){
        char[] abcCyr =   {'-','а','б','в','г','д','е','ё', 'ж','з','и','й','к','л','м','н','о','п','р','с','т','у','ф','х', 'ц','ч', 'ш','щ','ъ','ы','ь','э', 'ю','я','А','Б','В','Г','Д','Е','Ё', 'Ж','З','И','Й','К','Л','М','Н','О','П','Р','С','Т','У','Ф','Х', 'Ц', 'Ч','Ш', 'Щ','Ъ','Ы','Ь','Э','Ю','Я','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
        String[] abcLat = {"-","a","b","v","g","d","e","e","zh","z","i","y","k","l","m","n","o","p","r","s","t","u","f","h","ts","ch","sh","sch", "","i", "","e","yu","ya","A","B","V","G","D","E","E","Zh","Z","I","Y","K","L","M","N","O","P","R","S","T","U","F","H","Ts","Ch","Sh","Sch", "","I", "","E","yu","ya","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            for (int x = 0; x < abcCyr.length; x++ ) {
                if (message.charAt(i) == abcCyr[x]) {
                    builder.append(abcLat[x]);
                }
            }
        }
        return builder.toString();
    }
}
