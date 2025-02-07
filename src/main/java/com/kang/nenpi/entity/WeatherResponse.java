package com.kang.nenpi.entity;

public class WeatherResponse {
    private String date;
    private String icon;
    private String temp;
    private String description;

    // 생성자
    public WeatherResponse(String date, String icon, String temp, String description) {
        this.date = date;
        this.icon = icon;
        this.temp = temp;
        this.description = description;
    }

    // Getter 메서드
    public String getDate() { return date; }
    public String getIcon() { return icon; }
    public String getTemp() { return temp; }
    public String getDescription() { return description; }
}
