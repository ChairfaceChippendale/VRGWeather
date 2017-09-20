package com.ujujzk.vrgweather.ui.detail;


import com.orhanobut.logger.Logger;
import com.ujujzk.vrgweather.data.repository.WeatherRepository;
import com.ujujzk.vrgweather.data.repository.WeatherRepositoryImpl;

public class DetailPresenter implements DetailContract.Presenter {


    private DetailContract.View view;

    public DetailPresenter(DetailContract.View view) {
        this.view = view;
    }



}
