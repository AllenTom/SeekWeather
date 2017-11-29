package com.allen.seekweather.fragment.city_weather

import android.app.Fragment
import android.os.Bundle
import android.support.annotation.MainThread
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.allen.api.HeWeather.Response.HeWeatherSearch
import com.allen.seekweather.R
import com.allen.seekweather.activity.addcity.AddCityActivity
import com.allen.seekweather.event.CityWeatherListEvent
import kotlinx.android.synthetic.main.fragment_city_weather.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.jetbrains.anko.onClick
import org.jetbrains.anko.startActivity


class CityWeatherFragment : Fragment(), CityWeatherContract.View {
    private lateinit var adapter: CityWeatherRecyclerViewAdapter
    private val presenter: CityWeatherContract.Presenter = CityWeatherFragmentPresenterImpl(this)


    override fun LoadCityWeather(cityList: List<HeWeatherSearch.HeWeather.Basic>) {
        adapter = CityWeatherRecyclerViewAdapter(activity.applicationContext, cityList, presenter, activity)
        city_weather_recycler_view.layoutManager = LinearLayoutManager(activity.applicationContext)
        city_weather_recycler_view.adapter = adapter
    }

    override fun showOperationDialog() {
    }

    override fun setPresenter(presenter: CityWeatherContract.Presenter) {

    }

    override fun showSnackBar(message: String) {
        Snackbar.make(city_weather_recycler_view, message, Snackbar.LENGTH_SHORT)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_city_weather, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        EventBus.getDefault().register(this)
        presenter.loadRecyclerViewData()
        city_fragment_fab.onClick {
            activity.startActivity<AddCityActivity>()
        }


    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onRefreshCityList(event: CityWeatherListEvent) {
        presenter.loadRecyclerViewData()
    }
}
