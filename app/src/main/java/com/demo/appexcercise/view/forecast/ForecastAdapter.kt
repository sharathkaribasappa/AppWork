package com.demo.appexcercise.view.forecast

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.demo.appexcercise.R
import com.demo.appexcercise.model.FCWeather
import kotlinx.android.synthetic.main.forecast_item.view.*
import java.text.SimpleDateFormat

//adapter class for handling the forecast weather data
class ForecastAdapter(val forecastWeather: MutableList<FCWeather>) :
    RecyclerView.Adapter<ForecastAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ForecastAdapter.MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.forecast_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = forecastWeather.size

    override fun onBindViewHolder(holder: ForecastAdapter.MyViewHolder, position: Int) {
        holder.onBind(forecastWeather[position])
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(fCWeather: FCWeather) {
            itemView.day.text = parseDate(fCWeather.dt_txt)

            var temp_celcius = fCWeather.main.temp_max - 273.15
            itemView.fctempmax.text = "%.2f".format(temp_celcius)

            //forming the url to fetch icon based in tge weather info
            val iconUrl = "http://openweathermap.org/img/w/${fCWeather.weather[0].icon}.png"
            //glide call to fetch the icon
            Glide.with(itemView.context).load(iconUrl).into(itemView.fcicon)
        }
    }

    companion object {
        //helper method to parse the date obtained in forecast weather info
        fun parseDate(date : String): String {
            val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            val dat = format.parse(date)
            return android.text.format.DateFormat.format("EEEE", dat).toString()
        }
    }
}