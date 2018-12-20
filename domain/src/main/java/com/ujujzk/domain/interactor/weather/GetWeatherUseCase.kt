package com.ujujzk.domain.interactor.weather

import com.ujujzk.domain.executor.PostExecutionThread
import com.ujujzk.domain.gateway.WeatherGateway
import com.ujujzk.domain.interactor.SingleUseCase
import com.ujujzk.domain.model.CityWeather
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

class GetWeatherUseCase
@javax.inject.Inject constructor(
        postExecutionThread: PostExecutionThread,
        disposables: CompositeDisposable,
        private val weatherGateway: WeatherGateway
) : SingleUseCase<CityWeather, GetWeatherUseCase.Params>(postExecutionThread, disposables){

    override fun build(params: Params): Single<CityWeather> = weatherGateway.getWeather(params.cityName)

    class Params (val cityName: String){
        companion object {
            fun get(cityName: String) = Params(cityName)
        }
    }
}