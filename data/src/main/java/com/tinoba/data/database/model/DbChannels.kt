package com.tinoba.data.database.model

import androidx.room.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tinoba.domain.model.Channel
import java.lang.reflect.Type

@Entity(tableName = "channels")
data class DbChannels(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "title") val title: String,

    @ColumnInfo(name = "id") val id: Int,

    @TypeConverters(Converters::class)
    @ColumnInfo(name = "channels") val channels: List<Channel>,

    @ColumnInfo(name = "count") val count: Int,

    @ColumnInfo(name = "icon_asset") val iconAsset: String,

    @ColumnInfo(name = "cover_asset") val coverAsset: String

) {

    companion object {

        val EMPTY = DbChannels("", 0, emptyList(), 0, "", "")
    }

    class Converters {
        @TypeConverter
        fun fromString(value: String): List<Channel> {
            val listType: Type = object : TypeToken<List<Channel>>() {}.type
            return Gson().fromJson(value, listType)
        }

        @TypeConverter
        fun fromArrayList(list: List<Channel>): String {
            val gson = Gson()
            return gson.toJson(list)
        }
    }
}