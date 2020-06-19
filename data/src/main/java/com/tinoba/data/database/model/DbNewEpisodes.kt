package com.tinoba.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "new_episodes")
data class DbNewEpisodes(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "title") val title: String,

    @ColumnInfo(name = "url") val url: String,

    @ColumnInfo(name = "channel") val channel: String
) {

    companion object {

        val EMPTY = DbNewEpisodes("", "", "")
    }
}