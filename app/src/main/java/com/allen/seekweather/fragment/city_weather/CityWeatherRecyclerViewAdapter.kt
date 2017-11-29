package com.allen.seekweather.fragment.city_weather

import android.app.Activity
import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.allen.api.HeWeather.Response.HeWeatherNowWeatherResponse
import com.allen.api.HeWeather.Response.HeWeatherSearch
import com.allen.seekweather.R
import com.allen.seekweather.WeatherIconUtil
import com.allen.seekweather.activity.city_weather.CityWeatherActivity
import com.allen.seekweather.event.CityWeatherListEvent
import kotlinx.android.synthetic.main.card_weather_city.view.*
import org.greenrobot.eventbus.EventBus
import org.jetbrains.anko.onClick
import org.jetbrains.anko.startActivity

/**
 * Created by Allen on 2016/12/10.
 */

class CityWeatherRecyclerViewAdapter(val context: Context,
                                     private val cityList: List<HeWeatherSearch.HeWeather.Basic>, private val presenter: CityWeatherContract.Presenter,
                                     private val activity: Activity) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val inflater: LayoutInflater
    private val util = WeatherIconUtil()

    init {
        inflater = LayoutInflater.from(context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CityListViewHolder(inflater.inflate(R.layout.card_weather_city, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val pos = position
        holder.itemView.card_weather_city_city.text = cityList[position].city
        holder.itemView.card_weather_city_subtext.text = cityList[position].cnty
        presenter.getCityNowWeather(cityList[position].id!!, object : CityWeatherContract.Presenter.OnLoadCityWeather {
            override fun onSucceed(weather: HeWeatherNowWeatherResponse) {
                holder.itemView.card_weather_city_icon.setImageDrawable(ContextCompat.getDrawable(context, util.dayIcon[weather.heWeather5!![0].now!!.cond!!.code!!.toInt()]!!))
                holder.itemView.card_weather_city_weather.text = weather.heWeather5!![0].now.cond.txt
                holder.itemView.card_weather_city_tmp.text = "${weather.heWeather5!![0].now.tmp}°"
            }
        })
        holder.itemView.setOnCreateContextMenuListener { contextMenu, view, contextMenuInfo ->
            contextMenu.setHeaderTitle("请选择操作")
            contextMenu.add("设为主页").setOnMenuItemClickListener {
                presenter.setHomeCity(cityList[pos].id!!)
                true
            }
            contextMenu.add("删除").setOnMenuItemClickListener {
                presenter.deleteAddedCity(cityList[pos].id!!)
                EventBus.getDefault().post(CityWeatherListEvent())
                true
            }
        }
        holder.itemView.card_weather_city_bg.onClick {
            activity.startActivity<CityWeatherActivity>("CityId" to cityList[position].id!!)
        }

    }

    override fun getItemCount(): Int = cityList.size


    internal inner class CityListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}
