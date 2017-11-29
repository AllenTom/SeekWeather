package com.allen.seekweather.activity

import android.app.Fragment
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.allen.seekweather.R
import com.allen.seekweather.activity.about.AboutActivity
import com.allen.seekweather.fragment.city_weather.CityWeatherFragment
import com.allen.seekweather.fragment.main.MainFragment
import com.orhanobut.hawk.Hawk
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    lateinit var mainFragment: MainFragment
    lateinit var cityWeatherFragment : CityWeatherFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        Hawk.init(this).build()
        mainFragment = MainFragment()
        var homeCity: String = "北京"
        if(Hawk.contains("HomeCity")){
            homeCity = Hawk.get("HomeCity")
        }
        val mainFragmentBundle  = Bundle()
        mainFragmentBundle.putCharSequence("CityId",homeCity)
        mainFragment.arguments = mainFragmentBundle
        cityWeatherFragment = CityWeatherFragment()
        changeFragment(cityWeatherFragment)
        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {

        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val id = item.itemId

        if (id == R.id.nav_home) {
            changeFragment(mainFragment)
        } else if (id == R.id.nav_city) {
            changeFragment(cityWeatherFragment)

        } else if (id == R.id.nav_about) {
            startActivity<AboutActivity>()
        } else if (id == R.id.nav_exit) {
            finish()
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    fun changeFragment(fragment: Fragment) {
        val fragmentManager = fragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.main_container, fragment).commit()

    }
}
