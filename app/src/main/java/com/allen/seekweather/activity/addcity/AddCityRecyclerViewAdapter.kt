package com.allen.seekweather.activity.addcity

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.allen.api.HeWeather.Response.HeWeatherSearch
import com.allen.seekweather.R
import kotlinx.android.synthetic.main.card_city_search.view.*

/**
 * Created by Allen on 2016/12/14.
 */

class AddCityRecyclerViewAdapter(private val presenter: AddCityContract.Presenter, private val context: Context,
                                 private val search: HeWeatherSearch) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val layoutInflater: LayoutInflater

    init {
        layoutInflater = LayoutInflater.from(context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CityViewHolder(layoutInflater.inflate(R.layout.card_city_search, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
       val itemView = holder.itemView
       itemView.card_city_serch_text.text = search.heWeather!![position].basic!!.city
        itemView.card_city_serch_subtext!!.text = "${search.heWeather!![position].basic!!.cnty}  ${search.heWeather!![position].basic!!.prov}"
        itemView.card_city_serch_cb!!.setOnCheckedChangeListener { compoundButton, b ->
            if (b) {
                presenter.addCity(search.heWeather!![position].basic!!, context)
            }
        }
    }

    override fun getItemCount(): Int {
        return search.heWeather!!.size
    }

    internal inner class CityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
