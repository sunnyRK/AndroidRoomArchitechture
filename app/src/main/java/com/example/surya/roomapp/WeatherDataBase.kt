package com.example.surya.roomapp

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = arrayOf(WeatherData::class), version = 1)
abstract class WeatherDataBase : RoomDatabase() {
    abstract fun weatherDataDao(): WeatherDataDao
}