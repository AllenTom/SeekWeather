package com.allen.seekweather.repository

import android.content.Context
import com.allen.api.HeWeather.Response.HeWeatherSearch
import com.allen.seekweather.BaseDataListener

/**
 * Created by Allen on 2016/12/14.
 */

interface CityRepository {
    interface OnSearchCity : BaseDataListener<HeWeatherSearch>

    fun SearchCity(query: String, onSearchCity: OnSearchCity)

    fun AddCityWeatherList(city: HeWeatherSearch.HeWeather.Basic, context: Context)

    fun GetAddedCityList(): List<HeWeatherSearch.HeWeather.Basic>

    fun deleteCityWeather(cityId: String)
}
