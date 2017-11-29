package com.allen.seekweather.activity.future_weather

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import com.allen.api.HeWeather.Response.HeWeatherAllWeatherDataResponse
import com.allen.seekweather.R
import kotlinx.android.synthetic.main.activity_future_weather.*
import kotlinx.android.synthetic.main.content_future_weather.*

class FutureWeatherActivity : AppCompatActivity() {
    private lateinit var adapter: FutureWeatherAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_future_weather)
        setSupportActionBar(toolbar)
        val intent = intent
        val dailyForecast = intent.getSerializableExtra("Data") as HeWeatherAllWeatherDataResponse.HeWeather
        adapter = FutureWeatherAdapter(dailyForecast.daily_forecast, this)
        future_weather_recycler_view.layoutManager = LinearLayoutManager(this)
        future_weather_recycler_view.adapter = adapter
    }

}
