package com.tinoba.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tinoba.data.database.dao.CategoriesDao
import com.tinoba.data.database.dao.ChannelsDao
import com.tinoba.data.database.dao.NewEpisodesDao
import com.tinoba.data.database.model.DbCategories
import com.tinoba.data.database.model.DbChannels
import com.tinoba.data.database.model.DbNewEpisodes

@Database(entities = [DbChannels::class, DbCategories::class, DbNewEpisodes::class], version = MindvalleyDatabase.VERSION)
@TypeConverters(DbChannels.Converters::class)
abstract class MindvalleyDatabase : RoomDatabase() {

    companion object {

        const val VERSION = 1
        const val NAME = "MindvalleyDatabase"
    }

    abstract fun categories(): CategoriesDao

    abstract fun newEpisodes(): NewEpisodesDao

    abstract fun channels(): ChannelsDao
}