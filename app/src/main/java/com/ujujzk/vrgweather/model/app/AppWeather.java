package com.ujujzk.vrgweather.model.app;


import io.realm.RealmObject;

public class AppWeather extends RealmObject {

    private String dateStr;
    private long date;
    private String description;
    private String iconLink;

    private Double maxTemperature;
    private Double minTemperature;


    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIconLink() {
        return iconLink;
    }

    public void setIconLink(String iconLink) {
        this.iconLink = iconLink;
    }

    public Double getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(Double maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public Double getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(Double minTemperature) {
        this.minTemperature = minTemperature;
    }


    public String getMaxTempCelStr () {
        return getMaxTemperature() + "°C";
    }


    public String getMinTempCelStr () {
        return getMinTemperature() + "°C";
    }



}
