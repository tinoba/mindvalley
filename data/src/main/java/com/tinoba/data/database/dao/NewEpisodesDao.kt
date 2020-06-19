package com.tinoba.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tinoba.data.database.model.DbNewEpisodes
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface NewEpisodesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveEpisode(dbNewEpisodes: DbNewEpisodes)

    @Query("SELECT * FROM new_episodes")
    fun getNewEpisodes(): Single<List<DbNewEpisodes>>

    @Query("DELETE FROM new_episodes")
    fun clearNewEpisodes(): Completable
}