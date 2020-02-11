package com.example.dicWebBot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ((Runnable) () -> {
            SpringApplication.run(Application.class, args);
        }).run();

    }

}