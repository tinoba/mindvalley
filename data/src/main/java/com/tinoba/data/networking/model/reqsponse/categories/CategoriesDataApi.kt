package com.tinoba.data.networking.model.reqsponse.categories

import com.google.gson.annotations.SerializedName

data class CategoriesDataApi(@SerializedName("categories") val categories: List<CategoryApi>)