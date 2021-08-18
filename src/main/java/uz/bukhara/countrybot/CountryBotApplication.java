package uz.bukhara.countrybot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;

@SpringBootApplication
public class CountryBotApplication {

    public static void main(String[] args) {
        ApiContextInitializer.init();
        SpringApplication.run(CountryBotApplication.class, args);
    }

}
