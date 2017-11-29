package com.allen.seekweather.fragment.main

import com.allen.api.HeWeather.Response.HeWeatherAllWeatherDataResponse
import com.allen.seekweather.BasePresenterImpl
import com.allen.seekweather.repository.AppRepository
import com.allen.seekweather.repository.WeatherRepository

/**
 * Created by Allen on 2016/12/14.
 */

class MainFragmentPresenter(private val fragmentView: MainFragmentContract.View) :  MainFragmentContract.Presenter {
    var repository = AppRepository()
    override fun getHomeCityWeather(query: String) {
        repository.weatherRepository.GetGetCityAllWeatherInfo(query,
                object : WeatherRepository.OnGetCityAllWeatherInfo {
                    override fun onSucceed(callbackData: HeWeatherAllWeatherDataResponse) {
                        fragmentView.loadRecyclerViewData(callbackData)
                    }

                    override fun onError(throwable: Throwable) {
                        throwable.printStackTrace()
                        fragmentView.showSnackBar(throwable.message!!)
                    }
                })
    }
}
