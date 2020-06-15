package com.tinoba.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tinoba.data.database.model.DbChannels

@Database(entities = [DbChannels::class], version = MindvalleyDatabase.VERSION)
abstract class MindvalleyDatabase : RoomDatabase() {

    companion object {

        const val VERSION = 2
        const val NAME = "MindvalleyDatabase"
    }
}