package com.allen.seekweather.repository

import android.content.Context
import com.allen.api.HeWeather.Response.HeWeatherSearch
import com.orhanobut.hawk.Hawk
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.*

/**
 * Created by Allen on 2016/12/14.
 */

class CityRepositoryImpl : BaseRepository(), CityRepository {
    override fun SearchCity(query: String, onSearchCity: CityRepository.OnSearchCity) {
        BaseRepository.Companion.weatherapi.getSearchCity(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<HeWeatherSearch>() {
                    override fun onCompleted() {

                    }

                    override fun onError(e: Throwable) {
                        onSearchCity.onError(e)
                    }

                    override fun onNext(weatherSearch: HeWeatherSearch) {
                        onSearchCity.onSucceed(weatherSearch)
                    }
                })
    }

    override fun AddCityWeatherList(city: HeWeatherSearch.HeWeather.Basic, context: Context) {
        if (Hawk.contains("AddedCity")) {
            val basicList: MutableList<HeWeatherSearch.HeWeather.Basic> = Hawk.get<MutableList<HeWeatherSearch.HeWeather.Basic>>("AddedCity")
            basicList.add(city)
            Hawk.put("AddedCity", basicList)
        } else {
            val basicList: MutableList<HeWeatherSearch.HeWeather.Basic> = LinkedList()
            basicList.add(city)
            Hawk.put("AddedCity", basicList)
        }

    }

    override fun GetAddedCityList(): List<HeWeatherSearch.HeWeather.Basic> {
        var cityList: List<HeWeatherSearch.HeWeather.Basic>?
        cityList = Hawk.get<List<HeWeatherSearch.HeWeather.Basic>>("AddedCity")
        if (cityList == null) {
            cityList = ArrayList<HeWeatherSearch.HeWeather.Basic>()
        }
        return cityList
    }

    override fun deleteCityWeather(cityId: String) {
        val cities = Hawk.get<List<HeWeatherSearch.HeWeather.Basic>>("AddedCity")

        val iterator = cities.iterator()

        Hawk.put("AddedCity", cities)
    }

}
