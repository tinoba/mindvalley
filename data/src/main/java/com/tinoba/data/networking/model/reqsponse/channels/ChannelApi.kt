package com.tinoba.data.networking.model.reqsponse.channels

import com.google.gson.annotations.SerializedName
import com.tinoba.data.networking.model.reqsponse.CoverAssetApi

data class ChannelApi(
    @SerializedName("title") val title: String?,
    @SerializedName("series") val series: List<SeriesApi>?,
    @SerializedName("mediaCount") val mediaCount: Int?,
    @SerializedName("latestMedia") val courses: List<CourseApi>?,
    @SerializedName("id") val id: Int?,
    @SerializedName("iconAsset") val iconAsset: IconAssetApi?,
    @SerializedName("coverAsset") val coverAsset: CoverAssetApi?
)