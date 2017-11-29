package com.allen.api.HeWeather.Response

import com.google.gson.annotations.SerializedName

/**
 * Created by Allen on 2016/12/6.
 */

class HeWeatherForecastResponse {

    @SerializedName("HeWeather5")
    var heWeather: List<HeWeather>? = null

    class HeWeather {
        var basic: Basic? = null
        var status: String? = null
        var daily_forecast: List<DailyForecast>? = null

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

        class DailyForecast {
            /**
             * astro : {"mr":"11:58","ms":"23:09","sr":"07:21","ss":"16:49"}
             * cond : {"code_d":"101","code_n":"101","txt_d":"多云","txt_n":"多云"}
             * date : 2016-12-06
             * hum : 32
             * pcpn : 0.0
             * pop : 0
             * pres : 1024
             * tmp : {"max":"6","min":"-3"}
             * uv : 2
             * vis : 10
             * wind : {"deg":"149","dir":"无持续风向","sc":"微风","spd":"0"}
             */

            var astro: Astro? = null
            var cond: Cond? = null
            var date: String? = null
            var hum: String? = null
            var pcpn: String? = null
            var pop: String? = null
            var pres: String? = null
            var tmp: Tmp? = null
            var uv: String? = null
            var vis: String? = null
            var wind: Wind? = null

            class Astro {
                /**
                 * mr : 11:58
                 * ms : 23:09
                 * sr : 07:21
                 * ss : 16:49
                 */

                var mr: String? = null
                var ms: String? = null
                var sr: String? = null
                var ss: String? = null
            }

            class Cond {
                /**
                 * code_d : 101
                 * code_n : 101
                 * txt_d : 多云
                 * txt_n : 多云
                 */

                var code_d: String? = null
                var code_n: String? = null
                var txt_d: String? = null
                var txt_n: String? = null
            }

            class Tmp {
                /**
                 * max : 6
                 * min : -3
                 */

                var max: String? = null
                var min: String? = null
            }

            class Wind {
                /**
                 * deg : 149
                 * dir : 无持续风向
                 * sc : 微风
                 * spd : 0
                 */

                var deg: String? = null
                var dir: String? = null
                var sc: String? = null
                var spd: String? = null
            }
        }
    }
}
