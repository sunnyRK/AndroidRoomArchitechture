package com.example.surya.roomapp.ViewModel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Room
import com.example.surya.roomapp.WeatherData
import com.example.surya.roomapp.WeatherDataBase

class MainViewModel(application: Application) : AndroidViewModel(application) {

    val Db: WeatherDataBase
    var list: LiveData<MutableList<WeatherData>>
    init{
        Db = Room.databaseBuilder(application, WeatherDataBase::class.java,"weather.db").allowMainThreadQueries().build()
        list = Db.weatherDataDao().getAll()
    }
    fun fetchAllData() : LiveData<MutableList<WeatherData>>
    {

        list = Db.weatherDataDao().getAll()
        return  list
    }
}