package com.allen.api.HeWeather.Response

import com.google.gson.annotations.SerializedName

/**
 * Created by Allen on 2016/12/6.
 */

class HeWeatherHourlyResponse {

    @SerializedName("HeWeather5")
    var heWeather: List<HeWeather>? = null

    class HeWeather {
        /**
         * basic : {"city":"北京","cnty":"中国","id":"CN101010100","lat":"39.904000","lon":"116
         * .391000","update":{"loc":"2016-12-06
         * 10:51","utc":"2016-12-06 02:51"}}
         * hourly_forecast : [{"cond":{"code":"101","txt":"多云"},"date":"2016-12-06
         * 13:00","hum":"21","pop":"0","pres":"1021","tmp":"8","wind":{"deg":"139","dir":"西南风",
         * "sc":"微风","spd":"6"}},{"cond":{"code":"101","txt":"多云"},"date":"2016-12-06
         * 16:00","hum":"29","pop":"0","pres":"1020","tmp":"8","wind":{"deg":"137","dir":"西北风",
         * "sc":"微风","spd":"5"}},{"cond":{"code":"101","txt":"多云"},"date":"2016-12-06
         * 19:00","hum":"34","pop":"0","pres":"1021","tmp":"3","wind":{"deg":"69","dir":"西北风",
         * "sc":"微风","spd":"3"}},{"cond":{"code":"101","txt":"多云"},"date":"2016-12-06
         * 22:00","hum":"37","pop":"0","pres":"1022","tmp":"0","wind":{"deg":"207","dir":"西北风",
         * "sc":"微风","spd":"3"}}]
         * status : ok
         */

        var basic: Basic? = null
        var status: String? = null
        var hourly_forecast: List<HourlyForecast>? = null

        class Basic {
            /**
             * city : 北京
             * cnty : 中国
             * id : CN101010100
             * lat : 39.904000
             * lon : 116.391000
             * update : {"loc":"2016-12-06 10:51","utc":"2016-12-06 02:51"}
             */

            var city: String? = null
            var cnty: String? = null
            var id: String? = null
            var lat: String? = null
            var lon: String? = null
            var update: Update? = null

            class Update {
                /**
                 * loc : 2016-12-06 10:51
                 * utc : 2016-12-06 02:51
                 */

                var loc: String? = null
                var utc: String? = null
            }
        }

        class HourlyForecast {
            /**
             * cond : {"code":"101","txt":"多云"}
             * date : 2016-12-06 13:00
             * hum : 21
             * pop : 0
             * pres : 1021
             * tmp : 8
             * wind : {"deg":"139","dir":"西南风","sc":"微风","spd":"6"}
             */

            var cond: Cond? = null
            var date: String? = null
            var hum: String? = null
            var pop: String? = null
            var pres: String? = null
            var tmp: String? = null
            var wind: Wind? = null

            class Cond {
                /**
                 * code : 101
                 * txt : 多云
                 */

                var code: String? = null
                var txt: String? = null
            }

            class Wind {
                /**
                 * deg : 139
                 * dir : 西南风
                 * sc : 微风
                 * spd : 6
                 */

                var deg: String? = null
                var dir: String? = null
                var sc: String? = null
                var spd: String? = null
            }
        }
    }
}
