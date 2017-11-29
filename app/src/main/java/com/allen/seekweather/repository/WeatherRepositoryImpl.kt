package com.allen.seekweather.repository

import com.allen.api.Api
import com.allen.api.HeWeather.Response.HeWeatherAllWeatherDataResponse
import com.allen.api.HeWeather.Response.HeWeatherNowWeatherResponse
import com.orhanobut.hawk.Hawk

import org.joda.time.DateTime
import org.joda.time.Duration
import org.joda.time.Period
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter

import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by Allen on 2016/12/13.
 */

class WeatherRepositoryImpl : BaseRepository(), WeatherRepository {

    override fun GetGetCityAllWeatherInfo(query: String,
                                          onGetCityAllWeatherInfo: WeatherRepository.OnGetCityAllWeatherInfo) {
        if (Hawk.contains("$query.all.cache")) {
            val cache = Hawk.get<HeWeatherAllWeatherDataResponse>(query + ".all.cache")
            val formatter = DateTimeFormat.forPattern(Api.API_TIME_PATTERN)
            val updatedTime = DateTime.parse(cache.heWeather!![0].basic.update!!.loc, formatter)
            val nowTime = DateTime.now()
            val duration = Duration(updatedTime, nowTime)
            val period = Period(duration)
            if (period.toStandardMinutes().minutes > 60) {
                BaseRepository.Companion.weatherapi.getAllWeatherData(query)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(object : Subscriber<HeWeatherAllWeatherDataResponse>() {
                            override fun onCompleted() {

                            }

                            override fun onError(e: Throwable) {
                                onGetCityAllWeatherInfo.onError(e)
                            }

                            override fun onNext(
                                    heWeatherAllWeatherDataResponse: HeWeatherAllWeatherDataResponse) {
                                Hawk.put(heWeatherAllWeatherDataResponse.heWeather!![0].basic.id!! + ".all.cache",
                                        heWeatherAllWeatherDataResponse)
                                onGetCityAllWeatherInfo.onSucceed(heWeatherAllWeatherDataResponse)
                            }
                        })
            } else {
                onGetCityAllWeatherInfo.onSucceed(cache)
                return
            }
        }

        BaseRepository.Companion.weatherapi.getAllWeatherData(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<HeWeatherAllWeatherDataResponse>() {
                    override fun onCompleted() {

                    }

                    override fun onError(e: Throwable) {
                        onGetCityAllWeatherInfo.onError(e)
                    }

                    override fun onNext(
                            heWeatherAllWeatherDataResponse: HeWeatherAllWeatherDataResponse) {
                        onGetCityAllWeatherInfo.onSucceed(heWeatherAllWeatherDataResponse)
                    }
                })


    }

    override fun GetCityNowWeather(query: String,
                                   onGetCityNowWeather: WeatherRepository.OnGetCityNowWeather) {
        if (Hawk.contains(query + ".now.cache")) {
            val cache = Hawk.get<HeWeatherNowWeatherResponse>(query + ".now.cache")
            val formatter = DateTimeFormat.forPattern(Api.API_TIME_PATTERN)
            val updatedTime = DateTime.parse(cache.heWeather5!![0].basic!!.update!!.loc, formatter)
            val nowTime = DateTime.now()
            val duration = Duration(updatedTime, nowTime)
            val period = Period(duration)
            if (period.toStandardMinutes().minutes > 60) {
                BaseRepository.Companion.weatherapi.getCityNowWeather(query)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(object : Subscriber<HeWeatherNowWeatherResponse>() {
                            override fun onCompleted() {

                            }

                            override fun onError(e: Throwable) {
                                onGetCityNowWeather.onError(e)
                            }

                            override fun onNext(
                                    heWeatherNowWeatherResponse: HeWeatherNowWeatherResponse) {
                                Hawk.put(heWeatherNowWeatherResponse.heWeather5!![0].basic!!.id!! + ".now.cache",
                                        heWeatherNowWeatherResponse)
                                onGetCityNowWeather.onSucceed(heWeatherNowWeatherResponse)
                            }
                        })
            } else {
                onGetCityNowWeather.onSucceed(cache)
                return
            }
        }

        BaseRepository.Companion.weatherapi.getCityNowWeather(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<HeWeatherNowWeatherResponse>() {
                    override fun onCompleted() {

                    }

                    override fun onError(e: Throwable) {
                        onGetCityNowWeather.onError(e)
                    }

                    override fun onNext(
                            heWeatherNowWeatherResponse: HeWeatherNowWeatherResponse) {

                        Hawk.put(heWeatherNowWeatherResponse.heWeather5!![0].basic.id + ".now.cache", heWeatherNowWeatherResponse)
                        onGetCityNowWeather.onSucceed(heWeatherNowWeatherResponse)
                    }
                })

    }
}
