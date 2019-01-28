package com.demo.appexcercise.view.forecast

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.demo.appexcercise.R
import com.demo.appexcercise.Resource
import com.demo.appexcercise.model.FCWeather
import com.demo.appexcercise.model.ForecastWeather
import com.demo.appexcercise.view.current.PARAM_DATA
import kotlinx.android.synthetic.main.forecast_activity.*

class ForecastWeatherActivity : AppCompatActivity() {
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: ForecastWeatherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.forecast_activity)

        viewManager = LinearLayoutManager(this)

        recyclerView = findViewById<RecyclerView>(R.id.my_recycler_view).apply {
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = viewManager
        }

        title = "Forecast"
        initViewModel()
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(ForecastWeatherViewModel::class.java)
        viewModel.liveData.observe(this, Observer<Resource<ForecastWeather?>> { resource ->
            progress.visibility = View.GONE

            //Success scenario
            resource?.data.let {
                val list = resource?.data?.list as MutableList<FCWeather>
                recyclerView.adapter = ForecastAdapter(filterWeatherList(list))
            }

            //Error Scenario
            resource?.message.let {

            }
        })
        val id = intent.extras.get(PARAM_DATA) as Long
        progress.visibility = View.VISIBLE
        viewModel.load(id)
    }

    //Get the forecast weather list for 4 days for time 09 hours
    private fun filterWeatherList(list: MutableList<FCWeather>): MutableList<FCWeather> {
        var filterList = list.filter {
            it.dt_txt.contains("09:00:00")
        }.drop(1)

        return filterList as MutableList<FCWeather>
    }
}