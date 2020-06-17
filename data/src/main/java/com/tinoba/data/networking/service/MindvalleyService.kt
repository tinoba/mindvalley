package com.tinoba.data.networking.service

import com.tinoba.data.networking.model.reqsponse.channels.ChannelsApi
import com.tinoba.data.networking.model.reqsponse.newepisodes.NewEpisodesApi
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface MindvalleyService {

    companion object {

        private const val CHANNELS_ENDPOINT = "Xt12uVhM"
        private const val NEW_EPISODES_ENDPOINT = "z5AExTtw"
    }

    @GET(CHANNELS_ENDPOINT)
    fun getChannels(): Single<ChannelsApi>

    @GET(NEW_EPISODES_ENDPOINT)
    fun getNewEpisodes(): Single<NewEpisodesApi>
}