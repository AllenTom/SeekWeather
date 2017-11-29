package com.allen.api.HeWeather.Response

import com.google.gson.annotations.SerializedName

/**
 * Created by Allen on 2016/12/8.
 */

class HeWeatherSearch {

    @SerializedName("HeWeather5")
    var heWeather: List<HeWeather>? = null

    class HeWeather {
        /**
         * basic : {"city":"北京","cnty":"中国","id":"CN101010100","lat":"39.904000","lon":"116
         * .391000","prov":"直辖市"}
         * status : ok
         */

        var basic: Basic? = null
        var status: String? = null

        class Basic {
            /**
             * city : 北京
             * cnty : 中国
             * id : CN101010100
             * lat : 39.904000
             * lon : 116.391000
             * prov : 直辖市
             */

            var city: String? = null
            var cnty: String? = null
            var id: String? = null
            var lat: String? = null
            var lon: String? = null
            var prov: String? = null
        }
    }
}
