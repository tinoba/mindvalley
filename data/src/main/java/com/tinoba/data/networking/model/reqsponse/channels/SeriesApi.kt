package com.tinoba.data.networking.model.reqsponse.channels

import com.google.gson.annotations.SerializedName
import com.tinoba.data.networking.model.reqsponse.CoverAssetApi

data class SeriesApi(
    @SerializedName("title") val title: String,
    @SerializedName("coverAsset") val coverAsset: CoverAssetApi
)