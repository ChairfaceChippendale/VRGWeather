package com.ujujzk.vrgweather.model.app;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


public class AppCity extends RealmObject {

    @PrimaryKey
    private String cityName;
    private String countryCode;
    private long date;

    private RealmList<AppWeather> weatherList;


    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public List<AppWeather> getWeatherList() {
        return weatherList;
    }

    public void setWeatherList(List<AppWeather> weatherList) {
        if (this.weatherList == null) {
            this.weatherList = new RealmList<>();
        } else {
            this.weatherList.clear();
        }
        this.weatherList.addAll(weatherList);
    }
}
