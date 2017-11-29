package com.allen.seekweather.repository

/**
 * Created by Allen on 2016/12/14.
 */

class AppRepository {
    var weatherRepository: WeatherRepository = WeatherRepositoryImpl()
    var cityRepository: CityRepository = CityRepositoryImpl()
}
