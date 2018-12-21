package com.ujujzk.networking.di

import com.ujujzk.networking.OPEN_WEATHER_BASE_URL
import com.ujujzk.networking.interceptor.LoggingInterceptor
import com.ujujzk.networking.weather.OpenWeatherApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {

        val logging = LoggingInterceptor()
                .setLevel(LoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder()
                .addInterceptor(logging)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build()

    }

    @Provides
    @Singleton
    fun provideWeatherApi(client: OkHttpClient): OpenWeatherApi {
        return Retrofit.Builder()
                .baseUrl(OPEN_WEATHER_BASE_URL)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(OpenWeatherApi::class.java)
    }
}