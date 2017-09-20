package com.ujujzk.vrgweather.model;

import com.orhanobut.logger.Logger;
import com.ujujzk.vrgweather.model.app.AppCity;
import com.ujujzk.vrgweather.model.app.AppWeather;
import com.ujujzk.vrgweather.model.openweather.Example;
import com.ujujzk.vrgweather.model.openweather.WeatherList;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import rx.Observable;


public class OpenWeatherMapper {

    private static final String ICON_BASE_LINK = "http://openweathermap.org/img/w/";
    private static final String ICON_BASE_LINK_END = ".png";

    public static AppCity makeCity(Example example) {

        AppCity city = new AppCity();

        city.setCityName(example.getCity().getName());
        city.setCountryCode(example.getCity().getCountry());

        city.setDate((new GregorianCalendar()).getTimeInMillis());


        Observable.from(example.getList())

                .groupBy(openWeather -> {
                    Calendar cal = Calendar.getInstance();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
                    try {
                        cal.setTime(sdf.parse(openWeather.getDtTxt()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    return cal.get(Calendar.DAY_OF_MONTH);
                })
                .flatMap(groups -> groups.collect(AppWeather::new, (dayWeather, openWeather) -> {
                    dayWeather.setDate(openWeather.getDt());
                    dayWeather.setDateStr(openWeather.getDtTxt());
                    try {
                        dayWeather.setIconLink(ICON_BASE_LINK + openWeather.getWeather().get(0).getIcon() + ICON_BASE_LINK_END);
                        dayWeather.setDescription(openWeather.getWeather().get(0).getDescription());
                    } catch (IndexOutOfBoundsException iobe) {
                        Logger.e(iobe, "Can't parse open weather response");
                        dayWeather.setIconLink("");
                        dayWeather.setDescription("");
                    }

                    if (dayWeather.getMinTemperature() == null) {
                        dayWeather.setMinTemperature(openWeather.getMain().getTemp());
                    } else {
                        if (openWeather.getMain().getTemp() < dayWeather.getMinTemperature()) {
                            dayWeather.setMinTemperature(openWeather.getMain().getTemp());
                        }
                    }

                    if (dayWeather.getMaxTemperature() == null) {
                        dayWeather.setMaxTemperature((openWeather.getMain().getTemp()));
                    } else {
                        if (openWeather.getMain().getTemp() > dayWeather.getMaxTemperature()) {
                            dayWeather.setMaxTemperature(openWeather.getMain().getTemp());
                        }
                    }
                }))
                .toList()
                .subscribe(
                        city::setWeatherList,
                        Throwable::printStackTrace
                );


        return city;
    }



}
