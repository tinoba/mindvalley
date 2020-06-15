package com.tinoba.data.networking.model.reqsponse.channels

import com.google.gson.annotations.SerializedName

data class ChannelApi(
    @SerializedName("title") val title: String,
    @SerializedName("series") val series: List<SeriesApi>
)