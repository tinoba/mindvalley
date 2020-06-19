package com.tinoba.data.networking.model.reqsponse.newepisodes

import com.google.gson.annotations.SerializedName
import com.tinoba.data.networking.model.reqsponse.CoverAssetApi

data class NewEpisodeApi(
    @SerializedName("type") val type: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("coverAsset") val coverAsset: CoverAssetApi?,
    @SerializedName("channel") val channel: NewEpisodesChannelApi?
)