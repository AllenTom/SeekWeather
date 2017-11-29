package com.allen.seekweather.repository

import com.allen.api.HeWeather.Response.HeWeatherAllWeatherDataResponse
import com.allen.api.HeWeather.Response.HeWeatherNowWeatherResponse
import com.allen.seekweather.BaseDataListener

/**
 * Created by Allen on 2016/12/13.
 */

interface WeatherRepository {
    interface OnGetCityAllWeatherInfo : BaseDataListener<HeWeatherAllWeatherDataResponse>

    interface OnGetCityNowWeather : BaseDataListener<HeWeatherNowWeatherResponse>

    fun GetGetCityAllWeatherInfo(query: String, onGetCityAllWeatherInfo: OnGetCityAllWeatherInfo)

    fun GetCityNowWeather(query: String, onGetCityNowWeather: OnGetCityNowWeather)
}
