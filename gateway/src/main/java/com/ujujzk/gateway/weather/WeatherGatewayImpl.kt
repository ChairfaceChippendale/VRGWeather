package com.ujujzk.gateway.weather

import com.ujujzk.domain.gateway.WeatherGateway
import com.ujujzk.domain.model.City
import com.ujujzk.gateway.weather.sources.WeatherService
import com.ujujzk.gateway.weather.sources.WeatherStorage
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class WeatherGatewayImpl
    @Inject constructor(
            weatherService: WeatherService,
            weatherStorage: WeatherStorage
    ): WeatherGateway {

    override fun getWeather(city: String): Single<City> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getWeatherRemote(cityName: String): Single<City> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getWeatherLocal(cityName: String): Single<City> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCitiesLocal(): Observable<City> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}