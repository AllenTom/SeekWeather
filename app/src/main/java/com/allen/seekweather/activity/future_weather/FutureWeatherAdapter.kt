package com.allen.seekweather.activity.future_weather

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.allen.api.HeWeather.Response.HeWeatherAllWeatherDataResponse
import com.allen.seekweather.R
import com.allen.seekweather.WeatherIconUtil
import kotlinx.android.synthetic.main.card_future_weather.view.*

/**
 * Created by Allen on 2016/12/17.
 */

class FutureWeatherAdapter(
        private val dailyForecasts: List<HeWeatherAllWeatherDataResponse.DailyForecast>,
        private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val layoutInflater: LayoutInflater
    internal var util = WeatherIconUtil()

    init {
        layoutInflater = LayoutInflater.from(context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return FutureWeatherViewHolder(
                layoutInflater.inflate(R.layout.card_future_weather, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val dailyForecast = dailyForecasts[position]
        holder.itemView.future_weather_date.text = dailyForecast.date
        holder.itemView.future_weather_high.text = "${dailyForecast.tmp.max}°"
        holder.itemView.future_weather_low.text = "${dailyForecast.tmp.min}°"
        holder.itemView.future_weather_daytime_weather.text = dailyForecast.cond.txt_d
        holder.itemView.future_weather_night_weather.text = dailyForecast.cond.txt_n

        holder.itemView.future_weather_daytime_icon.setImageDrawable(ContextCompat.getDrawable(context, util.dayIcon[dailyForecast.cond.code_d.toInt()]!!))
        holder.itemView.future_weather_night_icon.setImageDrawable(ContextCompat.getDrawable(context, util.nightIcon[dailyForecast.cond.code_n.toInt()]!!))

        holder.itemView.future_weather_wind_dir.text = dailyForecast.wind.dir
        holder.itemView.future_weather_wind_spd.text = dailyForecast.wind.spd
        holder.itemView.future_weather_wind_level.text = dailyForecast.wind.sc
        holder.itemView.future_weather_sunrise.text = dailyForecast.astro.sr
        holder.itemView.future_weather_sunset.text = dailyForecast.astro.ss
        holder.itemView.future_weather_moonrise.text = dailyForecast.astro.mr
        holder.itemView.future_weather_moonset.text = dailyForecast.astro.ms
    }

    override fun getItemCount(): Int {
        return dailyForecasts.size
    }

    internal inner class FutureWeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
