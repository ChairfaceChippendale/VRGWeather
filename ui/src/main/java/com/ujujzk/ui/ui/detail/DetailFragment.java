package com.ujujzk.ui.ui.detail;

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
import android.widget.LinearLayout;

import com.ujujzk.ui.R;
import com.ujujzk.ui.model.VCity;


public class DetailFragment extends Fragment implements DetailContract.View {

    private FragmentDetailBinding binding;
    DetailContract.Presenter presenter;
    private VCity city;
    private WeatherListAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new DetailPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false);
        binding.toolbar.setTitle(city.getCityName());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initToolbar();
        initList();
    }

    private void initList () {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        binding.weatherList.setLayoutManager(linearLayoutManager);
        adapter = new WeatherListAdapter();
        binding.weatherList.setAdapter(adapter);
        binding.weatherList.setItemAnimator(new DefaultItemAnimator());
        binding.weatherList.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        adapter.populateWeathers(city.getWeatherList());
    }

    private void initToolbar() {
        binding.toolbar.setNavigationOnClickListener(v -> getActivity().onBackPressed());
        binding.toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
    }

    public void setCity(VCity cityName) {
        this.city = cityName;
    }
}
