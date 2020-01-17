package com.example.dicWebBot;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        new Runnable(){
            @Override
            public void run() {
                ApiContextInitializer.init();
                TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
                try {
                    telegramBotsApi.registerBot(new Bot());
                } catch (TelegramApiRequestException e) {
                    e.printStackTrace();
                }
            }
        }.run();

        SpringApplication.run(Application.class, args);
    }

}