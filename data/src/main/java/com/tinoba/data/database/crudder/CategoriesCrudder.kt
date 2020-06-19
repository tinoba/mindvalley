package com.tinoba.data.database.crudder

import io.reactivex.Completable
import io.reactivex.Single

interface CategoriesCrudder {

    fun saveCategory(category: String)

    fun getCategories(): Single<List<String>>

    fun clearCategories(): Completable
}