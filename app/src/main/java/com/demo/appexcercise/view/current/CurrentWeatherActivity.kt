package com.demo.appexcercise.view.current

import android.app.SearchManager
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.SearchView
import com.bumptech.glide.Glide
import com.demo.appexcercise.R
import com.demo.appexcercise.Resource
import com.demo.appexcercise.model.CurrentWeather
import com.demo.appexcercise.view.forecast.ForecastWeatherActivity
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.currentweather_activity.*
import java.util.*

const val PARAM_DATA = "PARAM_DATA"

class CurrentWeatherActivity : DaggerAppCompatActivity() {
    private lateinit var viewModel: CurrentWeatherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.currentweather_activity)

        initViewModel()
        handleIntent(intent)
    }

    override fun onNewIntent(intent: Intent) {
        handleIntent(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (menu.findItem(R.id.search).actionView as SearchView).apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
            setIconifiedByDefault(false)
            isFocusable = true
            requestFocusFromTouch()
        }
        return true
    }

    private fun handleIntent(intent: Intent) {
        // Verify the action and get the query
        if (Intent.ACTION_SEARCH == intent.action) {
            intent.getStringExtra(SearchManager.QUERY)?.also { query ->
                doMySearch(query)
            }
        }
    }

    private fun doMySearch(query: String) {
        progress.visibility = View.VISIBLE
        hideView()
        viewModel.load(query)
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(CurrentWeatherViewModel::class.java)
        viewModel.liveData.observe(this, Observer<Resource<CurrentWeather?>> { resource ->
            progress.visibility = View.GONE

            resource?.data?.let {
                showView()
                cityName.text = resource?.data?.name
                mainWeather.text = resource?.data?.weather!![0].main

                day.text = parseDate(resource?.data.dt)
                val temp_celcius = resource?.data.main!!.temp - 273.15
                temperature.text = "%.2f".format(temp_celcius)
                celcius.visibility = View.VISIBLE

                val iconUrl = "http://openweathermap.org/img/w/${resource?.data.weather[0].icon}.png"
                Glide.with(this).load(iconUrl).into(weatherIcon)

                forecast.setOnClickListener{
                    var intent = Intent(this,ForecastWeatherActivity::class.java)
                    intent.putExtra(PARAM_DATA, resource?.data.id)
                    startActivity(intent)
                }
            }

            resource?.message?.let {
                hideView()
                error.visibility = View.VISIBLE
            }
        })
    }

    private fun hideView() {
        error.visibility = View.GONE
        cityName.visibility = View.GONE
        mainWeather.visibility = View.GONE
        temperature.visibility = View.GONE
        celcius.visibility = View.GONE
        weatherIcon.visibility = View.GONE
        forecast.visibility = View.GONE
        day.visibility = View.GONE
    }

    private fun showView() {
        cityName.visibility = View.VISIBLE
        mainWeather.visibility = View.VISIBLE
        temperature.visibility = View.VISIBLE
        celcius.visibility = View.VISIBLE
        weatherIcon.visibility = View.VISIBLE
        forecast.visibility = View.VISIBLE
        day.visibility = View.VISIBLE
        error.visibility = View.GONE
    }

    private fun parseDate(date: Long): String {
        return android.text.format.DateFormat.format("EEEE", Date(date)).toString()
    }
}