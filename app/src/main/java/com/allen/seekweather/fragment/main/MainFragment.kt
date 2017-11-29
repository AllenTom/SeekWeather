package com.allen.seekweather.fragment.main

import android.app.Fragment
import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.allen.api.HeWeather.Response.HeWeatherAllWeatherDataResponse
import com.allen.seekweather.R
import com.allen.seekweather.event.HomeCityChangeEvent
import kotlinx.android.synthetic.main.fragment_main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe


class MainFragment : Fragment(), MainFragmentContract.View {
    private val presenter: MainFragmentContract.Presenter = MainFragmentPresenter(this)
    private lateinit var adapter: MainRecyclerViewAdapter
    override fun loadRecyclerViewData(weatherInfo: HeWeatherAllWeatherDataResponse) {
        adapter = MainRecyclerViewAdapter(activity.applicationContext, weatherInfo, activity)
        main_recyclerview.layoutManager = LinearLayoutManager(activity.applicationContext)
        main_recyclerview.adapter = adapter
    }

    override fun setPresenter(presenter: MainFragmentContract.Presenter) {
    }

    override fun showSnackBar(message: String) {
        Snackbar.make(main_recyclerview, message, Snackbar.LENGTH_SHORT).show()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        presenter.getHomeCityWeather(arguments.getString("CityId"))

        return inflater.inflate(R.layout.fragment_main, container, false)
    }


    override fun onAttach(context: Context?) {
        super.onAttach(context)
        EventBus.getDefault().register(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    // TODO cityID may null exception
    @Subscribe
    fun HomeCityChange(event: HomeCityChangeEvent) {
        presenter.getHomeCityWeather(event.cityId!!)
    }
}
