package com.ujujzk.vrgweather.ui.detail;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.ujujzk.vrgweather.databinding.ItemWeatherBinding;
import com.ujujzk.vrgweather.model.app.AppWeather;
import com.ujujzk.vrgweather.ui.main.CityListAdapter;

import java.util.List;


/**
 * Created by ujujzk on 19.09.2017
 * Softensy Digital Studio
 * softensiteam@gmail.com
 */

public class WeatherListAdapter extends RecyclerView.Adapter<WeatherListAdapter.WeatherViewHolder> {

    private List<AppWeather> weathers;

    @Override
    public WeatherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemWeatherBinding binding = ItemWeatherBinding.inflate(inflater, parent, false);
        return new WeatherListAdapter.WeatherViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(WeatherViewHolder holder, int position) {
        AppWeather weather = weathers.get(position);
        if (weather != null) {
            holder.binding.setWeather(weather);
        }
    }

    @Override
    public int getItemCount() {
        return weathers == null ? 0 : weathers.size();
    }

    public void populateWeathers(List<AppWeather> weathers) {
        this.weathers = weathers;
    }

    @BindingAdapter({"load_image"})
    public static void setImageViewResource(ImageView imageView, String imageUrl) {
        Picasso.with(imageView.getContext())
                .load(imageUrl)
                .into(imageView);
    }

    static class WeatherViewHolder extends RecyclerView.ViewHolder {
        ItemWeatherBinding binding;
        WeatherViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
