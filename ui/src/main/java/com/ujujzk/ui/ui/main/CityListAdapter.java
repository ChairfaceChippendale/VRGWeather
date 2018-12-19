package com.ujujzk.ui.ui.main;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ujujzk.ui.model.VCity;
import com.ujujzk.vrgweather.databinding.ItemCityBinding;
import com.ujujzk.vrgweather.model.app.AppCity;

import java.util.ArrayList;
import java.util.List;



public class CityListAdapter extends RecyclerView.Adapter<CityListAdapter.CityViewHolder> {

    private List<VCity> cities;



    public CityListAdapter() {

    }

    @Override
    public CityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemCityBinding binding = ItemCityBinding.inflate(inflater, parent, false);
        return new CityViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(CityViewHolder holder, int position) {
        VCity city = cities.get(position);
        if (city != null) {
            holder.binding.setCity(city);
        }

    }

    @Override
    public int getItemCount() {
        return cities == null ? 0 : cities.size();
    }

    public void populateCities(List<VCity> cities) {
        this.cities = cities;
        notifyDataSetChanged();
    }

    public void addCity(VCity city) {
        if (cities == null) {
            cities = new ArrayList<>();
        }
        if (!cities.contains(city)) {
            cities.add(city);
            notifyDataSetChanged();
        }
    }

    @Nullable
    public VCity getItem(int position) {
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

            binding.getRoot().setOnClickListener(v -> {});
            binding.removeBtn.setOnClickListener(v -> {});

        }
    }
}
