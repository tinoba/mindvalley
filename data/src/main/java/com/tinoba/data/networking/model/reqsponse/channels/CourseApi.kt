package com.tinoba.data.networking.model.reqsponse.channels

import com.google.gson.annotations.SerializedName
import com.tinoba.data.networking.model.reqsponse.CoverAssetApi

data class CourseApi(
    @SerializedName("type") val type: String,
    @SerializedName("title") val title: String,
    @SerializedName("coverAsset") val coverAsset: CoverAssetApi
)