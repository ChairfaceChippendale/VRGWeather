package com.ujujzk.vrgweather.ui.main;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ujujzk.vrgweather.databinding.ItemCityBinding;
import com.ujujzk.vrgweather.model.app.AppCity;

import java.util.ArrayList;
import java.util.List;

import rx.subjects.PublishSubject;


public class CityListAdapter extends RecyclerView.Adapter<CityListAdapter.CityViewHolder> {

    private List<AppCity> cities;

    PublishSubject<Integer> clickRemove;
    PublishSubject<Integer> clickCity;

    public CityListAdapter() {
        clickRemove = PublishSubject.create();
        clickCity = PublishSubject.create();
    }

    @Override
    public CityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemCityBinding binding = ItemCityBinding.inflate(inflater, parent, false);
        return new CityViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(CityViewHolder holder, int position) {
        AppCity city = cities.get(position);
        if (city != null) {
            holder.binding.setCity(city);
        }

    }

    @Override
    public int getItemCount() {
        return cities == null ? 0 : cities.size();
    }

    public void populateCities(List<AppCity> cities) {
        this.cities = cities;
        notifyDataSetChanged();
    }

    public void addCity(AppCity city) {
        if (cities == null) {
            cities = new ArrayList<>();
        }
        if (!cities.contains(city)) {
            cities.add(city);
            notifyDataSetChanged();
        }
    }

    @Nullable
    public AppCity getItem(int position) {
        if (cities != null) {
            return cities.get(position);
        }
        return null;
    }

    class CityViewHolder extends RecyclerView.ViewHolder {
        ItemCityBinding binding;

        CityViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);

            binding.getRoot().setOnClickListener(v -> clickCity.onNext(getAdapterPosition()));
            binding.removeBtn.setOnClickListener(v -> clickRemove.onNext(getAdapterPosition()));

        }
    }
}
