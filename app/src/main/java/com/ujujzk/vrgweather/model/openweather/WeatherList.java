package com.ujujzk.vrgweather.model.openweather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class WeatherList {
    @SerializedName("dt")
    @Expose
    private Long dt; //time ms
    @SerializedName("main")
    @Expose
    private Main main;
    @SerializedName("weather")
    @Expose
    private java.util.List<Weather> weather = null;
    @SerializedName("wind")
    @Expose
    private Wind wind;
    @SerializedName("dt_txt")
    @Expose
    private String dtTxt; //date and time as a string


    public Long getDt() {
        return dt;
    }

    public void setDt(Long dt) {
        this.dt = dt;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public java.util.List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(java.util.List<Weather> weather) {
        this.weather = weather;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public String getDtTxt() {
        return dtTxt;
    }

    public void setDtTxt(String dtTxt) {
        this.dtTxt = dtTxt;
    }
}
