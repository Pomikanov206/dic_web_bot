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
            case 2: return MONDAY;
            case 3: return TUESDAY;
            case 4: return WENESDAY;
            case 5: return THURSDAY;
            case 6: return FRIDAY;
            default:return MONDAY;
        }
    }
}
