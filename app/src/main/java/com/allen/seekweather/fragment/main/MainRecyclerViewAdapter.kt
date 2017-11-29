package com.allen.seekweather.fragment.main

import android.app.Activity
import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.allen.api.HeWeather.Response.HeWeatherAllWeatherDataResponse
import com.allen.seekweather.R
import com.allen.seekweather.WeatherIconUtil
import com.allen.seekweather.activity.future_weather.FutureWeatherActivity
import kotlinx.android.synthetic.main.card_footer.view.*
import kotlinx.android.synthetic.main.card_suggestion.view.*
import kotlinx.android.synthetic.main.card_weather_aqi.view.*
import kotlinx.android.synthetic.main.card_weather_detail.view.*
import kotlinx.android.synthetic.main.card_weather_forecast.view.*
import kotlinx.android.synthetic.main.card_weather_now.view.*
import org.jetbrains.anko.onClick
import org.jetbrains.anko.startActivity

/**
 * Created by Allen on 2016/12/14.
 */

class MainRecyclerViewAdapter internal constructor(private val context: Context, private val weatherData: HeWeatherAllWeatherDataResponse,
                                                   private val activity: Activity) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val layoutInflater: LayoutInflater
    private val iconUtil: WeatherIconUtil = WeatherIconUtil()

    init {
        this.layoutInflater = LayoutInflater.from(context)
    }

    private enum class Item {
        ITEM_NOW,
        ITEM_FORECAST,
        ITEM_DETAIL,
        ITEM_AQI,
        ITEM_SUGGESTION,
        ITEM_FOOTER
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder? {
        if (viewType == Item.ITEM_NOW.ordinal) {
            return NowWeatherViewHolder(
                    layoutInflater.inflate(R.layout.card_weather_now, parent, false))
        } else if (viewType == Item.ITEM_FORECAST.ordinal) {
            return WeatherForecastViewHolder(
                    layoutInflater.inflate(R.layout.card_weather_forecast, parent, false))
        } else if (viewType == Item.ITEM_DETAIL.ordinal) {
            return DetailViewHolder(
                    layoutInflater.inflate(R.layout.card_weather_detail, parent, false))
        } else if (viewType == Item.ITEM_AQI.ordinal) {
            return AQIViewHolder(
                    layoutInflater.inflate(R.layout.card_weather_aqi, parent, false))
        } else if (viewType == Item.ITEM_SUGGESTION.ordinal) {
            return SuggestionViewHolder(
                    layoutInflater.inflate(R.layout.card_suggestion, parent, false))
        } else if (viewType == Item.ITEM_FOOTER.ordinal) {
            return FooterViewHolder(
                    layoutInflater.inflate(R.layout.card_footer, parent, false))
        } else {
            return null
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val itemView: View = holder.itemView
        val weather = weatherData.heWeather!![0]
        if (holder is NowWeatherViewHolder) {

            itemView.card_now_city.text = weather.basic.city
            itemView.card_now_temp.text = "${weather.now.tmp}°"
            itemView.card_now_now.text = weather.now.cond.txt
            itemView.card_now_image.setImageDrawable(ContextCompat.getDrawable(context,
                    iconUtil.dayIcon[Integer.parseInt(weather.now.cond.code)]!!
            ))


        } else if (holder is WeatherForecastViewHolder) {
            for (i in 0..2) {
                holder.highTextViews[i].text = "${weather.daily_forecast[i].tmp.max}°"
            }
            for (i in 0..2) {
                holder.lowTextViews[i].text = "${weather.daily_forecast[i].tmp.min!!}°"
            }
            for (i in 0..2) {
                holder.dayWeatherTextViews[i].text = weather.daily_forecast[i].cond.txt_d
            }
            for (i in 0..2) {
                holder.nightWeatherTextViews[i].text = weather.daily_forecast[i].cond.txt_n
            }
            for (i in 0..2) {
                holder.dayIconImageViews[i].setImageDrawable(
                        ContextCompat.getDrawable(context,
                                iconUtil.dayIcon[Integer.parseInt(weather.daily_forecast[i].cond.code_d.trim { it <= ' ' })]!!
                        ))
            }
            for (i in 0..2) {
                holder.nightIconImageViews[i].setImageDrawable(
                        ContextCompat.getDrawable(context,
                                iconUtil.nightIcon.get(
                                        Integer.parseInt(weather.daily_forecast[i].cond.code_n.trim { it <= ' ' })
                                )!!
                        ))
            }

            holder.itemView.card_daily_weather_more.onClick {
                activity.startActivity<FutureWeatherActivity>("Data" to weather)
            }



        } else if (holder is DetailViewHolder) {
            itemView.card_detail_feel.text = "${weather.now.fl}°"
            itemView.card_detail_humidity.text = "${weather.now.hum}%"
            itemView.card_detail_press.text = weather.now.pres
            itemView.card_detail_wind_spd.text = "${weather.now.wind.spd}km/h"
            itemView.card_detail_wind_dir!!.text = weather.now.wind.dir
            itemView.card_detail_wind_level.text = "${weather.now.wind.sc}级"

        } else if (holder is AQIViewHolder) {
            itemView.card_aqi_aqi.text = weather.aqi.city.aqi
            itemView.card_aqi_qlty!!.text = weather.aqi.city.qlty
            itemView.card_aqi_pm10!!.text = weather.aqi.city.pm10
            itemView.card_aqi_pm25!!.text = weather.aqi.city.pm25
        } else if (holder is SuggestionViewHolder) {
            itemView.card_suggest_cold.text = weather.suggestion.flu.txt
            itemView.card_suggest_conf.text = weather.suggestion.comf.txt
            itemView.card_suggest_cw.text = weather.suggestion.cw.txt
            itemView.card_suggest_wear.text = weather.suggestion.drsg.txt
            itemView.card_suggest_sport.text = weather.suggestion.sport.txt
            itemView.card_suggest_uv.text = weather.suggestion.uv.txt
        } else if (holder is FooterViewHolder) {
            itemView.card_footer_last_update.text = "最后更新:  ${weather.basic.update.loc}"
        }
    }

    override fun getItemCount(): Int {
        return 6
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0) {
            return Item.ITEM_NOW.ordinal
        } else if (position == 1) {
            return Item.ITEM_FORECAST.ordinal
        } else if (position == 2) {
            return Item.ITEM_DETAIL.ordinal
        } else if (position == 3) {
            return Item.ITEM_AQI.ordinal
        } else if (position == 4) {
            return Item.ITEM_SUGGESTION.ordinal
        } else if (position == 5) {
            return Item.ITEM_FOOTER.ordinal
        } else {
            return -1
        }
    }


    internal inner class NowWeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    internal inner class DetailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    internal inner class AQIViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    internal inner class SuggestionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    internal inner class FooterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    internal inner class WeatherForecastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val highTextViews: Array<TextView> = arrayOf(itemView.card_daily_high1, itemView.card_daily_high2, itemView.card_daily_high3)
        val lowTextViews: Array<TextView> = arrayOf(itemView.card_daily_low1, itemView.card_daily_low2, itemView.card_daily_low3)
        val dayWeatherTextViews: Array<TextView> = arrayOf(itemView.card_daily_weather_day1, itemView.card_daily_weather_day2, itemView.card_daily_weather_day3)
        val nightWeatherTextViews: Array<TextView> = arrayOf(itemView.card_daily_weather_night1, itemView.card_daily_weather_night2, itemView.card_daily_weather_night3)
        val dayIconImageViews: Array<ImageView> = arrayOf(itemView.card_daily_weather_day_icon1, itemView.card_daily_weather_day_icon2, itemView.card_daily_weather_day_icon3)
        val nightIconImageViews: Array<ImageView> = arrayOf(itemView.card_daily_weather_night_icon1, itemView.card_daily_weather_night_icon2, itemView.card_daily_weather_night_icon3)

    }

}
