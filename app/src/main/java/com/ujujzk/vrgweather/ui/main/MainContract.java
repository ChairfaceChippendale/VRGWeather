package com.ujujzk.vrgweather.ui.main;


import com.ujujzk.vrgweather.model.app.AppCity;

import java.util.List;

public interface MainContract {

    interface View {

        void onForecastLoadSuccess(AppCity city);

        void onForecastLoadFail();

        void showProgress(boolean show);

        void onCitiesLoadSuccess(List<AppCity> cities);

        void onCitiesLoadFail();
    }

    interface Presenter {

        void findForecast(String query);

        void getCities();
    }
}
