package com.example.dicWebBot.model.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="bot_users")
public class User {
    @Id
    private String chatId;

    private String userGroup;

    public User(){

    }

    public User(String chatId, String userGroup) {
        this.chatId = chatId;
        this.userGroup = userGroup;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public String getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(String userGroup) {
        this.userGroup = userGroup;
    }
}
