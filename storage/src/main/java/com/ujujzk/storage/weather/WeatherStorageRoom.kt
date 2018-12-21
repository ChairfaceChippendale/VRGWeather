package com.ujujzk.storage.weather

import com.ujujzk.domain.model.CityWeather
import com.ujujzk.gateway.weather.sources.WeatherStorage
import com.ujujzk.storage.weather.model.CityWeatherFromDomainToRoomMapper
import io.reactivex.Single
import javax.inject.Inject

class WeatherStorageRoom
    @Inject constructor(val weatherDao: WeatherDao, val cityDao: CityDao): WeatherStorage {

    override fun putCityWeather(cityWeather: CityWeather) : Single<String> {
        return Single.just(cityWeather)
                .map ( CityWeatherFromDomainToRoomMapper() )
                .map{f ->
                    cityDao.insert(f.city)
                    weatherDao.insertAll(f.weather)
                    return@map "City weather saved to local"
                }

    }

    override fun getAllCityWeather(): Single<List<CityWeather>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCityWeather(cityId: Long): Single<CityWeather> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun findCityWeather(cityName: String): Single<CityWeather> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteCityWeather(cityId: Long) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun cleanUpCityWeather() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}