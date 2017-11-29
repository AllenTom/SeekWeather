package com.allen.api.HeWeather.Response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Allen on 2016/12/6.
 */

class HeWeatherNowWeatherResponse {
    @SerializedName("HeWeather5")
    var heWeather5: List<HeWeather5Bean>? = null

    data class HeWeather5Bean(
            var basic: Basic,
            var now: Now,
            var status: String
    ) : Serializable


    data class Basic(
            var city: String,
            var cnty: String,
            var id: String,
            var lat: String,
            var lon: String,
            var update: Update,
            var srjkgfhk:String
    ) : Serializable


    data class Update(
            var loc: String,
            var utc: String
    )


    class Now(
            var cond: Cond,
            var fl: String,
            var hum: String,
            var pcpn: String,
            var pres: String,
            var tmp: String,
            var vis: String,
            var wind: Wind
    ) : Serializable


    data class Cond(
            var code: String,
            var txt: String
    )

    data class Wind(
            var deg: String,
            var dir: String,
            var sc: String,
            var spd: String
    )
}
