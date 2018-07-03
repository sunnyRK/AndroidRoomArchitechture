package com.example.surya.roomapp

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query



@Dao
interface WeatherDataDao {

    @Query("SELECT * from weatherData")
    fun getAll(): LiveData<MutableList<WeatherData>>

    @Insert
    fun insert(weatherData: WeatherData)

    @Query("DELETE from weatherData")
    fun deleteAll()

}