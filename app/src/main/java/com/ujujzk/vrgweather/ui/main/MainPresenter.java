package com.ujujzk.vrgweather.ui.main;


import com.orhanobut.logger.Logger;
import com.ujujzk.vrgweather.data.repository.WeatherRepository;
import com.ujujzk.vrgweather.data.repository.WeatherRepositoryImpl;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View view;
    WeatherRepository repository;

    public MainPresenter(MainContract.View view) {
        this.view = view;
        repository = WeatherRepositoryImpl.getInstance();

    }

    @Override
    public void findForecast(String query) {
        view.showProgress(true);
        repository.getWeather(query.trim())
                .doAfterTerminate(() -> view.showProgress(false))
                .subscribe(
                        appCity -> view.onForecastLoadSuccess(appCity),
                        throwable -> {
                            Logger.e(throwable, "Can't load city weather");
                            view.onForecastLoadFail();
                        });
    }

    @Override
    public void getCities() {
        view.showProgress(true);
        repository.getCitiesLocal()
                .toList()
                .doAfterTerminate(() -> view.showProgress(false))
                .subscribe(
                        cities -> {
                            view.onCitiesLoadSuccess(cities);
                        },
                        throwable -> {
                            view.onCitiesLoadFail();
                        }
                );
    }
}
