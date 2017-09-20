package com.ujujzk.vrgweather.ui.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ujujzk.vrgweather.R;
import com.ujujzk.vrgweather.databinding.FragmentCitiesBinding;
import com.ujujzk.vrgweather.model.app.AppCity;

import java.util.List;


public class CitiesFragment extends Fragment {

    FragmentCitiesBinding binding;
    MainContract.Presenter presenter;

    CityListAdapter cityListAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cities, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initCityList();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        cityListAdapter.clickCity.onCompleted();
        cityListAdapter.clickRemove.onCompleted();
    }

    private void initCityList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        binding.cityList.setLayoutManager(linearLayoutManager);
        cityListAdapter = new CityListAdapter();
        binding.cityList.setAdapter(cityListAdapter);
        binding.cityList.setItemAnimator(new DefaultItemAnimator());
        binding.cityList.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        presenter.getCities();

        cityListAdapter.clickCity.subscribe(this::onCityClick);
        cityListAdapter.clickRemove.subscribe(this::onCityDelete);
    }

    private void onCityClick(int position) {
        AppCity city = cityListAdapter.getItem(position);
        if (city != null) {
            presenter.findForecast(city.getCityName());
        }
    }
    
    private void onCityDelete(int position) {
        // TODO: 20.09.2017
    }



    public void setPresenter(MainContract.Presenter presenter) {
        this.presenter = presenter;
    }


    public void setCities (List<AppCity> cities) {
        cityListAdapter.populateCities(cities);

    }



    public void addCity(AppCity city) {
        cityListAdapter.addCity(city);
    }
}
