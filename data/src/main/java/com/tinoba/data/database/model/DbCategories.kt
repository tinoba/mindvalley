package com.tinoba.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class DbCategories(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "category") val category: String
) {

    companion object {

        val EMPTY = DbCategories("")
    }
}