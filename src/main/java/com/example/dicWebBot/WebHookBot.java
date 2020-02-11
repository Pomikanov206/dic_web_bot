package com.example.dicWebBot;

import org.telegram.telegrambots.api.methods.BotApiMethod;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramWebhookBot;

public class WebHookBot extends TelegramWebhookBot {
    /**
     * Метод для приема сообщений.
     * @param update Содержит сообщение от пользователя.
     */
    @Override
    public BotApiMethod onWebhookUpdateReceived(Update update) {
        if(update.hasMessage() && update.getMessage().hasText()) {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(update.getMessage().getChatId().toString());
            sendMessage.setText("Работает web-hook вариант. Ваше сообщение: " + update.getMessage().getText());
            return sendMessage;
        }
        return null;
    }

    /**
     * Метод возвращает имя бота, указанное при регистрации.
     * @return имя бота
     */
    @Override
    public String getBotUsername() {
        return "comp_int_tech_dik_bot";
    }

    /**
     * Метод возвращает token бота для связи с сервером Telegram
     * @return token для бота
     */
    @Override
    public String getBotToken() {
        return "961764432:AAGTjCRAoA6uWGQQvTeW_OCCJULyqWuKqcU";
    }

    /**
     * Метод возвращает адрес web-hook для связи с сервером Telegram
     * @return token для бота
     */
    @Override
    public String getBotPath() {
        return "webhook";
    }
}
