package com.example.surya.roomapp

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "weatherData")


data class WeatherData(@PrimaryKey(autoGenerate = true) var id: Long?,
                       @ColumnInfo(name = "humidity") var humidity: String

) {
    constructor() : this(null, "")
}