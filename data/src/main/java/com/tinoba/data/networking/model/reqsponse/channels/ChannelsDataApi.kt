package com.tinoba.data.networking.model.reqsponse.channels

import com.google.gson.annotations.SerializedName

data class ChannelsDataApi(@SerializedName("channels") val channels: List<ChannelApi>)