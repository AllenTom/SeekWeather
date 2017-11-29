package com.allen.seekweather.fragment.city_weather

import com.allen.api.HeWeather.Response.HeWeatherNowWeatherResponse
import com.allen.api.HeWeather.Response.HeWeatherSearch
import com.allen.seekweather.BasePresenter
import com.allen.seekweather.BaseView

/**
 * Created by Allen on 2016/12/14.
 */

interface CityWeatherContract {
    interface View : BaseView<Presenter> {
        fun LoadCityWeather(cityList: List<HeWeatherSearch.HeWeather.Basic>)

        fun showOperationDialog()
    }

    interface Presenter : BasePresenter {
        interface OnLoadCityWeather{
            fun onSucceed(weather : HeWeatherNowWeatherResponse)
        }
        fun loadRecyclerViewData()


        fun getCityNowWeather(query: String, listener : OnLoadCityWeather)

        fun setHomeCity(cityId: String)

        fun operationDialog()

        fun deleteAddedCity(cityId: String)
    }
}
