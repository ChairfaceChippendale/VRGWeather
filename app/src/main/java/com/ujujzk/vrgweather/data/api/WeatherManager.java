package com.ujujzk.vrgweather.data.api;



import com.ujujzk.vrgweather.model.openweather.Example;

import rx.Single;

public interface WeatherManager {

    Single<Example> getWeather (String cityName);


}
