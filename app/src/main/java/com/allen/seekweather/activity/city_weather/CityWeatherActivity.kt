package com.allen.seekweather.activity.city_weather

import android.app.Fragment
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.allen.seekweather.R
import com.allen.seekweather.fragment.main.MainFragment
import kotlinx.android.synthetic.main.activity_city_weather.*

class CityWeatherActivity : AppCompatActivity() {
    private lateinit var mainFragment: MainFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city_weather)
        setSupportActionBar(toolbar)

        mainFragment = MainFragment()
        val cityId = intent.getStringExtra("CityId")
        val bundle = Bundle(1)
        bundle.putString("CityId", cityId)
        mainFragment.arguments = bundle
        changeFragment(mainFragment)
    }


    fun changeFragment(fragment: Fragment) {
        val fragmentManager = fragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.city_weather_container, fragment).commit()

    }

}
