package com.ujujzk.storage.weather

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.ujujzk.storage.weather.model.CityRoom
import io.reactivex.Single

@Dao
interface CityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(city: CityRoom)

    @Query("SELECT * FROM cityroom WHERE id=:id")
    fun get(id: Long) : Single<CityRoom>

    @Query("SELECT * FROM cityroom")
    fun getAll() : Single<List<CityRoom>>

    @Query("DELETE FROM cityroom WHERE id=:id")
    fun delete(id: Long)

    @Query("DELETE FROM cityroom WHERE date<:date")
    fun deleteOld(date: Long)
}