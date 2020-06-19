package com.tinoba.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tinoba.data.database.model.DbCategories
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface CategoriesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveCategory(dbCategories: DbCategories)

    @Query("SELECT * FROM categories")
    fun getCategories(): Single<List<DbCategories>>

    @Query("DELETE FROM categories")
    fun clearCategories(): Completable
}