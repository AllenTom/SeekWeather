package com.allen.seekweather.activity.addcity

import android.content.Context
import com.allen.api.HeWeather.Response.HeWeatherSearch
import com.allen.seekweather.BasePresenter
import com.allen.seekweather.BaseView

/**
 * Created by Allen on 2016/12/14.
 */

interface AddCityContract {
    interface View : BaseView<Presenter> {
        fun addRecyclerData(search: HeWeatherSearch)
    }

    interface Presenter : BasePresenter {
        fun initRecyclerViewData(weatherSearch: HeWeatherSearch)

        fun showSearchResult(query: String)

        fun addCity(city: HeWeatherSearch.HeWeather.Basic, context: Context)
    }

}
