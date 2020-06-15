package com.tinoba.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "channels")
data class DbChannels(
    @PrimaryKey(autoGenerate = false) @ColumnInfo(name = "id") val id: Int
) {

    companion object {

        val EMPTY = DbChannels(0)
    }
}