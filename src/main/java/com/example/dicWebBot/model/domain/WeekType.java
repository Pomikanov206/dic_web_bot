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
}
