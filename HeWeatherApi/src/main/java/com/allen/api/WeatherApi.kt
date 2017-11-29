package com.allen.api

import com.allen.api.HeWeather.Response.HeWeatherSearch
import com.allen.api.HeWeather.Response.HeWeatherAllWeatherDataResponse
import com.allen.api.HeWeather.Response.HeWeatherForecastResponse
import com.allen.api.HeWeather.Response.HeWeatherHourlyResponse
import com.allen.api.HeWeather.Response.HeWeatherNowWeatherResponse
import com.allen.api.HeWeather.Response.HeWeatherSuggestionResponse

import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

/**
 * Created by Allen on 2016/12/5.
 */

interface WeatherApi {

    @GET("now")
    fun getCityNowWeather(@Query("city") city: String): Observable<HeWeatherNowWeatherResponse>

    @GET("forecast")
    fun getCityForecast(@Query("city") city: String): Observable<HeWeatherForecastResponse>

    @GET("hourly")
    fun getHourlyForecast(@Query("city") city: String): Observable<HeWeatherHourlyResponse>

    @GET("suggestion")
    fun getWeatherSuggestion(@Query("city") city: String): Observable<HeWeatherSuggestionResponse>

    @GET("weather")
    fun getAllWeatherData(@Query("city") city: String): Observable<HeWeatherAllWeatherDataResponse>

    @GET("search")
    fun getSearchCity(@Query("city") query: String): Observable<HeWeatherSearch>

}
