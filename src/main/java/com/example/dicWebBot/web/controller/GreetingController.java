package com.example.dicWebBot.web.controller;

import com.example.dicWebBot.model.repos.LessonRepo;
import com.example.dicWebBot.telegram.controller.BotController;
import com.example.dicWebBot.model.domain.User;
import com.example.dicWebBot.model.repos.UserRepo;
import com.example.dicWebBot.telegram.view.Bot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

@Controller
public class GreetingController {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private LessonRepo lessonRepo;

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);

        userRepo.save(new User("1", "CS"));

        System.out.println("USER ADDED!!!");

        return "greeting";
    }

    @GetMapping("/starting")
    public String start(Model model) {
        ((Runnable) () -> {
            ApiContextInitializer.init();
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
            try {
                Bot telegramBot = new Bot();
                telegramBot.setController(new BotController(telegramBot, userRepo, lessonRepo));
                telegramBotsApi.registerBot(telegramBot);

            } catch (TelegramApiRequestException e) {
                e.printStackTrace();
            }
        }).run();
        return "starting";
    }

}
