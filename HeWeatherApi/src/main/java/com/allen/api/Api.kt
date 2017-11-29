package com.allen.api

import java.util.concurrent.TimeUnit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Allen on 2016/12/5.
 */
object Api {
    val API_TIME_PATTERN = "yyyy-MM-dd HH:mm"

    val service: WeatherApi
        get() {
            val client = OkHttpClient.Builder()
                    .addInterceptor(ApiInterceptor())
                    .retryOnConnectionFailure(true)
                    .connectTimeout(5, TimeUnit.SECONDS)
                    .build()

            val retrofit = Retrofit.Builder()
                    .baseUrl("https://free-api.heweather.com/v5/")
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()
            return retrofit.create<WeatherApi>(WeatherApi::class.java)
        }
}
