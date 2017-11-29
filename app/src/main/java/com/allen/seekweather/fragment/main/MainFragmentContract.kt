package com.allen.seekweather.fragment.main

import com.allen.api.HeWeather.Response.HeWeatherAllWeatherDataResponse
import com.allen.seekweather.BasePresenter
import com.allen.seekweather.BaseView

/**
 * Created by Allen on 2016/12/13.
 */

interface MainFragmentContract {
    interface View : BaseView<Presenter> {
        fun loadRecyclerViewData(weatherInfo: HeWeatherAllWeatherDataResponse)
    }

    interface Presenter : BasePresenter {
        fun getHomeCityWeather(query: String)

    }
}
