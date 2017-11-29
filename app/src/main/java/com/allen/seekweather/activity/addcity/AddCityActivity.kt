package com.allen.seekweather.activity.addcity

import android.Manifest
import android.content.Context
import android.location.LocationManager
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.widget.Toast
import com.allen.api.HeWeather.Response.HeWeatherSearch
import com.allen.seekweather.R
import com.miguelcatalan.materialsearchview.MaterialSearchView
import com.orhanobut.hawk.Hawk
import com.tbruyelle.rxpermissions.RxPermissions
import kotlinx.android.synthetic.main.activity_add_city.*
import kotlinx.android.synthetic.main.content_add_city.*

class AddCityActivity : AppCompatActivity(), AddCityContract.View, MaterialSearchView.OnQueryTextListener {
    private val presenter = AddCityPresenterImpl(this)
    private var locationProvider: String? = null
    private var locationManager: LocationManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_city)
        setSupportActionBar(toolbar)
        Hawk.init(this).build()
        search_view.setOnQueryTextListener(this)


        val rxPermissions = RxPermissions(this)
        rxPermissions.request(Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION)
                .subscribe { aBoolean ->
                    if (!aBoolean) {
                        Toast.makeText(applicationContext, "Permission Grad",
                                Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(applicationContext, "Permission not Grad",
                                Toast.LENGTH_SHORT).show()
                    }
                }

        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        //获取所有可用的位置提供器
        val providers = locationManager!!.getProviders(true)
        if (providers.contains(LocationManager.GPS_PROVIDER)) {
            //如果是GPS
            locationProvider = LocationManager.GPS_PROVIDER
        } else if (providers.contains(LocationManager.NETWORK_PROVIDER)) {
            //如果是Network
            locationProvider = LocationManager.NETWORK_PROVIDER
        } else {
            Toast.makeText(this, "没有可用的位置提供器", Toast.LENGTH_SHORT).show()
            return
        }
        //获取Location
        val location = locationManager!!.getLastKnownLocation(locationProvider)
        if (location != null) {
            //不为空,显示地理位置经纬度
            add_city_position.text = location.latitude.toString() + "   " + location.longitude
        }

    }


    override fun setPresenter(presenter: AddCityContract.Presenter) {

    }

    override fun showSnackBar(message: String) {
        Snackbar.make(add_city_recyclerview, message, Snackbar.LENGTH_SHORT).show()
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        presenter.showSearchResult(query)
        search_view.closeSearch()
        return true
    }

    override fun onQueryTextChange(newText: String): Boolean {
        return false
    }

    override fun addRecyclerData(search: HeWeatherSearch) {
        add_city_recyclerview.layoutManager = LinearLayoutManager(this)
        add_city_recyclerview.adapter = AddCityRecyclerViewAdapter(presenter, this, search)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.add_city_search, menu)

        val item = menu.findItem(R.id.action_search)
        search_view.setMenuItem(item)

        return true
    }
}
