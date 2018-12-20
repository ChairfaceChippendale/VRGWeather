package com.ujujzk.storage.weather

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.ujujzk.storage.weather.model.WeatherRoom
import io.reactivex.Single

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(weather: List<WeatherRoom>)

    @Query("SELECT * FROM weatherroom WHERE id=:id")
    fun get(id: Long) : Single<WeatherRoom>

    @Query("SELECT * FROM weatherroom WHERE cityId=:cityId")
    fun getForCity(cityId: Long) : Single<List<WeatherRoom>>

    @Query("DELETE FROM weatherroom WHERE id=:id")
    fun delete(id: Long)

    @Query("DELETE FROM weatherroom WHERE cityId=:cityId")
    fun deleteForCity(cityId: Long)

    @Query("DELETE FROM weatherroom WHERE date<:date")
    fun deleteOld(date: Long)
}