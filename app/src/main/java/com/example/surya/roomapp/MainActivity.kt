package com.example.surya.roomapp;

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.arch.persistence.room.Room
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.StaggeredGridLayoutManager
import com.example.surya.roomapp.RoomAdapter.RoomAdap
import com.example.surya.roomapp.ViewModel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.sql.Timestamp

public class MainActivity : AppCompatActivity() {

    lateinit var adapterObj: RoomAdap

    private val viewModel2: MainViewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    private val changeObserver =
            Observer<MutableList<WeatherData>> {
                value -> value?.let { ChangeUi(value) }
            }
    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        val Db = Room.databaseBuilder(applicationContext,WeatherDataBase::class.java,"weather.db").allowMainThreadQueries().build()
        viewModel2.list.observe(this, changeObserver)
        ViewModelCalling()

        addNote.setOnClickListener {
            val weather = WeatherData()
            val Timestampkey:Long = Timestamp(System.currentTimeMillis()).time
            weather.id = Timestampkey
            weather.humidity = Timestampkey.toString()
            Db.weatherDataDao().insert(weather)
            ViewModelCalling()

        }

        delNote.setOnClickListener {
            Db.weatherDataDao().deleteAll()
            ViewModelCalling()
        }

    }

    private fun ViewModelCalling()
    {
        viewModel2.fetchAllData().observe(this, object : Observer<MutableList<WeatherData>> {
            override fun onChanged(listWeather: MutableList<WeatherData>?) {
                adapterObj = RoomAdap(applicationContext, listWeather!!)
                rvRoom?.layoutManager = StaggeredGridLayoutManager(2, 1)
                rvRoom?.itemAnimator = DefaultItemAnimator()
                rvRoom?.adapter = adapterObj
                adapterObj.notifyDataSetChanged()
            }
        })
    }

    private fun ChangeUi(listWeather: MutableList<WeatherData>)
    {
        adapterObj = RoomAdap(applicationContext, listWeather!!)
        rvRoom?.layoutManager = StaggeredGridLayoutManager(2, 1)
        rvRoom?.itemAnimator = DefaultItemAnimator()
        rvRoom?.adapter = adapterObj
        adapterObj.notifyDataSetChanged()
    }
}
