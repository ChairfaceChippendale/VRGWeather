package com.ujujzk.vrgweather.ui.main;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ujujzk.vrgweather.R;
import com.ujujzk.vrgweather.databinding.ActivityMainBinding;
import com.ujujzk.vrgweather.model.app.AppCity;
import com.ujujzk.vrgweather.ui.detail.DetailFragment;

import java.util.List;


public class MainActivity extends AppCompatActivity implements MainContract.View {

    private ActivityMainBinding binding;
    MainContract.Presenter presenter;
    private CitiesFragment citiesFragment;
    private SearchFragment searchFragment;
    private DetailFragment detailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        presenter = new MainPresenter(this);

        initToolbar();
        initViewPager();
    }


    @Override
    public void onForecastLoadSuccess(AppCity city) {
        detailFragment = new DetailFragment();
        detailFragment.setCity(city);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(android.R.id.content, detailFragment)
                .addToBackStack("detail")
                .commit();

        if (citiesFragment.isAdded()) {
            citiesFragment.addCity(city);
        }
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onForecastLoadFail() {
        // TODO: 19.09.2017
    }

    private void initToolbar() {
        binding.toolbar.setTitle(R.string.main_act_title);
    }

    private void initViewPager() {
        MainPagerAdapter adapter = new MainPagerAdapter(getSupportFragmentManager());

        searchFragment = new SearchFragment();
        searchFragment.setPresenter(presenter);
        adapter.addFrag(searchFragment, getString(R.string.main_act_tab_search).toUpperCase());

        citiesFragment = new CitiesFragment();
        citiesFragment.setPresenter(presenter);
        adapter.addFrag(citiesFragment, getString(R.string.main_act_tab_cities).toUpperCase());


        binding.viewpager.setAdapter(adapter);
        binding.tablayout.setupWithViewPager(binding.viewpager);
    }

    @Override
    public void showProgress(boolean show) {
        // TODO: 19.09.2017
    }


    @Override
    public void onCitiesLoadSuccess(List<AppCity> cities) {
        if (citiesFragment.isAdded()) {
            citiesFragment.setCities(cities);
        }
    }

    @Override
    public void onCitiesLoadFail() {
        // TODO: 20.09.2017
    }
}
