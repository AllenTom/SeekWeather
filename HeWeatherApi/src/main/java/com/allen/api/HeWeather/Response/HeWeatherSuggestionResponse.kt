package com.allen.api.HeWeather.Response

import com.google.gson.annotations.SerializedName

/**
 * Created by Allen on 2016/12/6.
 */

class HeWeatherSuggestionResponse {

    @SerializedName("HeWeather5")
    var heWeather: List<HeWeather>? = null

    class HeWeather {
        /**
         * basic : {"city":"永川","cnty":"中国","id":"CN101040200","lat":"29.251000","lon":"105
         * .853000","update":{"loc":"2016-12-06
         * 10:51","utc":"2016-12-06 02:51"}}
         * status : ok
         * suggestion : {"air":{"brf":"中","txt":"气象条件对空气污染物稀释、扩散和清除无明显影响，易感人群应适当减少室外活动时间。"},
         * "comf":{"brf":"舒适","txt":"白天不太热也不太冷，风力不大，相信您在这样的天气条件下，应会感到比较清爽和舒适。"},
         * "cw":{"brf":"较适宜","txt":"较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。"},"drsg":{"brf":"较冷",
         * "txt":"建议着厚外套加毛衣等服装。年老体弱者宜着大衣、呢外套加羊毛衫。"},"flu":{"brf":"较易发",
         * "txt":"天气较凉，较易发生感冒，请适当增加衣服。体质较弱的朋友尤其应该注意防护。"},"sport":{"brf":"较适宜",
         * "txt":"天气较好，但考虑气温较低，推荐您进行室内运动，若户外适当增减衣物并注意防晒。"},"trav":{"brf":"适宜",
         * "txt":"天气较好，但丝毫不会影响您出行的心情。温度适宜又有微风相伴，适宜旅游。"},"uv":{"brf":"弱",
         * "txt":"紫外线强度较弱，建议出门前涂擦SPF在12-15之间、PA+的防晒护肤品。"}}
         */

        var basic: Basic? = null
        var status: String? = null
        var suggestion: Suggestion? = null

        class Basic {
            /**
             * city : 永川
             * cnty : 中国
             * id : CN101040200
             * lat : 29.251000
             * lon : 105.853000
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

        class Suggestion {
            /**
             * air : {"brf":"中","txt":"气象条件对空气污染物稀释、扩散和清除无明显影响，易感人群应适当减少室外活动时间。"}
             * comf : {"brf":"舒适","txt":"白天不太热也不太冷，风力不大，相信您在这样的天气条件下，应会感到比较清爽和舒适。"}
             * cw : {"brf":"较适宜","txt":"较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。"}
             * drsg : {"brf":"较冷","txt":"建议着厚外套加毛衣等服装。年老体弱者宜着大衣、呢外套加羊毛衫。"}
             * flu : {"brf":"较易发","txt":"天气较凉，较易发生感冒，请适当增加衣服。体质较弱的朋友尤其应该注意防护。"}
             * sport : {"brf":"较适宜","txt":"天气较好，但考虑气温较低，推荐您进行室内运动，若户外适当增减衣物并注意防晒。"}
             * trav : {"brf":"适宜","txt":"天气较好，但丝毫不会影响您出行的心情。温度适宜又有微风相伴，适宜旅游。"}
             * uv : {"brf":"弱","txt":"紫外线强度较弱，建议出门前涂擦SPF在12-15之间、PA+的防晒护肤品。"}
             */

            var air: Air? = null
            var comf: Comf? = null
            var cw: Cw? = null
            var drsg: Drsg? = null
            var flu: Flu? = null
            var sport: Sport? = null
            var trav: Trav? = null
            var uv: Uv? = null

            class Air {
                /**
                 * brf : 中
                 * txt : 气象条件对空气污染物稀释、扩散和清除无明显影响，易感人群应适当减少室外活动时间。
                 */

                var brf: String? = null
                var txt: String? = null
            }

            class Comf {
                /**
                 * brf : 舒适
                 * txt : 白天不太热也不太冷，风力不大，相信您在这样的天气条件下，应会感到比较清爽和舒适。
                 */

                var brf: String? = null
                var txt: String? = null
            }

            class Cw {
                /**
                 * brf : 较适宜
                 * txt : 较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。
                 */

                var brf: String? = null
                var txt: String? = null
            }

            class Drsg {
                /**
                 * brf : 较冷
                 * txt : 建议着厚外套加毛衣等服装。年老体弱者宜着大衣、呢外套加羊毛衫。
                 */

                var brf: String? = null
                var txt: String? = null
            }

            class Flu {
                /**
                 * brf : 较易发
                 * txt : 天气较凉，较易发生感冒，请适当增加衣服。体质较弱的朋友尤其应该注意防护。
                 */

                var brf: String? = null
                var txt: String? = null
            }

            class Sport {
                /**
                 * brf : 较适宜
                 * txt : 天气较好，但考虑气温较低，推荐您进行室内运动，若户外适当增减衣物并注意防晒。
                 */

                var brf: String? = null
                var txt: String? = null
            }

            class Trav {
                /**
                 * brf : 适宜
                 * txt : 天气较好，但丝毫不会影响您出行的心情。温度适宜又有微风相伴，适宜旅游。
                 */

                var brf: String? = null
                var txt: String? = null
            }

            class Uv {
                /**
                 * brf : 弱
                 * txt : 紫外线强度较弱，建议出门前涂擦SPF在12-15之间、PA+的防晒护肤品。
                 */

                var brf: String? = null
                var txt: String? = null
            }
        }
    }
}
