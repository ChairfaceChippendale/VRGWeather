package com.ujujzk.vrgweather.data.api;

import android.app.Application;
import android.support.annotation.IntRange;

import com.readystatesoftware.chuck.ChuckInterceptor;
import com.ujujzk.vrgweather.BuildConfig;
import com.ujujzk.vrgweather.model.openweather.Example;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Single;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class OpenWeatherManager implements WeatherManager {

    private static final String OPEN_WEATHER_BASE_URL = "http://api.openweathermap.org";
    private static final String OPEN_WEATHER_UNITS = "metric";
    private static final String OPEN_WEATHER_APP_ID = BuildConfig.OPEN_WEATHER_APP_ID;





    private static volatile OpenWeatherManager instance;

    private OpenWeatherManager() {
        //Prevent form the reflection api.
        if (instance != null) {
            throw new AssertionError("Use getInstance() method to get the single instance of this class.");
        }
    }

    public static OpenWeatherManager getInst() {
        if (instance == null) {
            synchronized (OpenWeatherManager.class) {
                if (instance == null) {
                    instance = new OpenWeatherManager();
                }
            }
        }
        return instance;
    }


    @Override
    public Single<Example> getWeather(String cityName) {
        Retrofit weatherClient = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(OPEN_WEATHER_BASE_URL)
                .client(provideHttpClient(60))
                .build();
        OpenWeatherApi weatherAPI = weatherClient.create(OpenWeatherApi.class);


        return weatherAPI.getWeather(cityName, OPEN_WEATHER_APP_ID, OPEN_WEATHER_UNITS, Locale.getDefault().getLanguage())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }


    private OkHttpClient provideHttpClient(@IntRange(from = 0, to = 1000) int waitingTime) {
        return new OkHttpClient.Builder()
//                .addInterceptor(new ChuckInterceptor(app.getApplicationContext()))
                .connectTimeout(waitingTime, TimeUnit.SECONDS)
                .readTimeout(waitingTime, TimeUnit.SECONDS)
                .build();
    }
}
