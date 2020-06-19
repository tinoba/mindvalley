package com.tinoba.data.database.crudder

import com.tinoba.data.database.dao.CategoriesDao
import com.tinoba.data.database.model.DbCategories
import io.reactivex.Completable
import io.reactivex.Single

class CategoriesCruderImpl(private val categoriesDao: CategoriesDao) : CategoriesCrudder {

    override fun saveCategory(category: String) = categoriesDao.saveCategory(DbCategories(category))

    override fun getCategories(): Single<List<String>> = categoriesDao.getCategories().map { it.map { it.category } }

    override fun clearCategories(): Completable = Completable.fromAction { categoriesDao.clearCategories() }
}