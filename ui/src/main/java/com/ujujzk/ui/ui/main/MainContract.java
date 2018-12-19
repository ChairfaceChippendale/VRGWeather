package com.ujujzk.ui.ui.main;


import com.ujujzk.ui.model.VCity;

import java.util.List;

public interface MainContract {

    interface View {

        void onForecastLoadSuccess(VCity city);

        void onForecastLoadFail();

        void showProgress(boolean show);

        void onCitiesLoadSuccess(List<VCity> cities);

        void onCitiesLoadFail();
    }

    interface Presenter {

        void findForecast(String query);

        void getCities();
    }
}
