package com.tinoba.data.networking.model.reqsponse.channels

import com.google.gson.annotations.SerializedName

data class SeriesApi(
    @SerializedName("title") val title: String,
    @SerializedName("coverAsset") val coverAsset: CoverAssetApi
)

data class CoverAssetApi(@SerializedName("url") val url: String)