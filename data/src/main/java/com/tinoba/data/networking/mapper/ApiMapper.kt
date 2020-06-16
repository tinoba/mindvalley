package com.tinoba.data.networking.mapper

import com.tinoba.data.networking.model.reqsponse.channels.ChannelsApi
import com.tinoba.domain.model.Channels

interface ApiMapper {

    fun mapToChannels(channelsApi: ChannelsApi): List<Channels>
}