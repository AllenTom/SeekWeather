package com.allen.seekweather.activity.addcity

import android.content.Context
import com.allen.api.HeWeather.Response.HeWeatherSearch
import com.allen.seekweather.event.CityWeatherListEvent
import com.allen.seekweather.repository.AppRepository
import com.allen.seekweather.repository.CityRepository
import org.greenrobot.eventbus.EventBus

/**
 * Created by Allen on 2016/12/14.
 */

class AddCityPresenterImpl(private val activityView: AddCityContract.View) : AddCityContract.Presenter {
    private val repository = AppRepository()
    override fun initRecyclerViewData(weatherSearch: HeWeatherSearch) {

    }

    override fun showSearchResult(query: String) {
        repository.cityRepository.SearchCity(query, object : CityRepository.OnSearchCity {
            override fun onSucceed(callbackData: HeWeatherSearch) {
                activityView.addRecyclerData(callbackData)
            }

            override fun onError(throwable: Throwable) {
                throwable.printStackTrace()
            }
        })
    }

    override fun addCity(city: HeWeatherSearch.HeWeather.Basic, context: Context) {
        repository.cityRepository.AddCityWeatherList(city, context)
        activityView.showSnackBar("添加 ${city.city} 成功")

        EventBus.getDefault().post(CityWeatherListEvent())

    }
}
