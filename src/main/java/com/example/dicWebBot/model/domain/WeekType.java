package com.example.dicWebBot.model.domain;

public enum WeekType {
    NONE("Завжди"),
    NUMERATOR("Чисельник"),
    DENOMINATOR("Знаменник");

    private String type;

    WeekType(String type) {
        this.type = type;
    }

    public String getType(){
        return type;
    }

    public static WeekType getType(int number) {
        if (number%2 == 1)
            return DENOMINATOR;
        else
            return NUMERATOR;
    }

}
