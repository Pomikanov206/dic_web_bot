package com.example.dicWebBot.model.domain;

public enum Days {
    MONDAY("Понеділок"),
    TUESDAY("Вівторок"),
    WENESDAY("Середа"),
    THURSDAY("Четверг"),
    FRIDAY("П'ятниця");

    private String day;

    Days(String day) {
        this.day = day;
    }

    public String getDay() {
        return day;
    }
}
