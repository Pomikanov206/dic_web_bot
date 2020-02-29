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

    public static Days getDay(int day) {
        switch (day){
            case 1: return MONDAY;
            case 2: return TUESDAY;
            case 3: return WENESDAY;
            case 4: return THURSDAY;
            case 5: return FRIDAY;
            default:return MONDAY;
        }
    }
}
