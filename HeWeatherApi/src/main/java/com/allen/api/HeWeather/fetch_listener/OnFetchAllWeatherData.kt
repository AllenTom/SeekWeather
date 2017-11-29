package com.allen.api.HeWeather.fetch_listener

import com.allen.api.HeWeather.Response.HeWeatherAllWeatherDataResponse

/**
 * Created by Allen on 2016/12/6.
 */

interface OnFetchAllWeatherData {
    fun onSucceed(weatherDataResponse: HeWeatherAllWeatherDataResponse)

    fun onError(throwable: Throwable)
}
