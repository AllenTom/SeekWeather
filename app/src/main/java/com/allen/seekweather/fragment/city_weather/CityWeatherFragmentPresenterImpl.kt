package com.allen.seekweather.fragment.city_weather

import com.allen.api.HeWeather.Response.HeWeatherNowWeatherResponse
import com.allen.seekweather.event.HomeCityChangeEvent
import com.allen.seekweather.repository.AppRepository
import com.allen.seekweather.repository.WeatherRepository
import com.orhanobut.hawk.Hawk
import org.greenrobot.eventbus.EventBus

/**
 * Created by Allen on 2016/12/14.
 */

class CityWeatherFragmentPresenterImpl(private val fragmentView: CityWeatherContract.View) : CityWeatherContract.Presenter {
    private val repository = AppRepository()


    override fun loadRecyclerViewData() {
        val cityList = repository.cityRepository.GetAddedCityList()
        fragmentView.LoadCityWeather(cityList)
    }

    override fun getCityNowWeather(query: String, listener: CityWeatherContract.Presenter.OnLoadCityWeather) {
        repository.weatherRepository.GetCityNowWeather(query, object : WeatherRepository.OnGetCityNowWeather {
            override fun onError(throwable: Throwable) {
                throwable.printStackTrace()
            }

            override fun onSucceed(callbackData: HeWeatherNowWeatherResponse) {
                listener.onSucceed(callbackData)
            }
        })
    }

    override fun setHomeCity(cityId: String) {
        Hawk.put("HomeCity", cityId)
        EventBus.getDefault().post(HomeCityChangeEvent(cityId))
    }

    override fun operationDialog() {
        fragmentView.showOperationDialog()
    }

    override fun deleteAddedCity(cityId: String) {
        repository.cityRepository.deleteCityWeather(cityId)
    }
}
