package com.example.dicWebBot.model;

public interface ILesson {
    void createLesson();
    String readLesson(String groupName, String day);
    void updateLesson();
    void deleteLesson();
}
