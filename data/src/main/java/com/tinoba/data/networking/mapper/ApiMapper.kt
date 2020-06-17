package com.tinoba.data.networking.mapper

import com.tinoba.data.networking.model.reqsponse.channels.ChannelsApi
import com.tinoba.data.networking.model.reqsponse.newepisodes.NewEpisodesApi
import com.tinoba.domain.model.Channels
import com.tinoba.domain.model.NewEpisode

interface ApiMapper {

    fun mapToChannels(channelsApi: ChannelsApi): List<Channels>

    fun mapToNewEpisodes(newEpisodesApi: NewEpisodesApi): List<NewEpisode>
}