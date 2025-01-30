package com.kang.nenpi.entity;

public class WeatherResponse {
    private String description;  // 날씨 상태 (맑음, 흐림 등)
    private String temperature;  // 온도
    private String humidity;     // 습도
    private String icon;         // 아이콘 (🌤, ☁️ 등)

    public WeatherResponse() {}

    public WeatherResponse(String description, String temperature, String humidity, String icon) {
        this.description = description;
        this.temperature = temperature;
        this.humidity = humidity;
        this.icon = icon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    // Getter & Setter
}
