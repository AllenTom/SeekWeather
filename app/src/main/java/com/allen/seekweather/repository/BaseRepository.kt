package com.allen.seekweather.repository

import com.allen.api.Api
import com.allen.api.WeatherApi

/**
 * Created by Allen on 2016/12/13.
 */

open class BaseRepository {
    companion object {
        var weatherapi = Api.service
    }

}
