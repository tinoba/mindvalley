package com.tinoba.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tinoba.data.database.model.DbChannels
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface ChannelsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveChannel(dbChannels: DbChannels)

    @Query("SELECT * FROM channels")
    fun getChannels(): Single<List<DbChannels>>

    @Query("DELETE FROM channels")
    fun clearChannels(): Completable
}