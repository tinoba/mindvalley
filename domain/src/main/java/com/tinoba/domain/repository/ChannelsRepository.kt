package com.tinoba.domain.repository

import com.tinoba.domain.model.Channels
import com.tinoba.domain.model.NewEpisode
import io.reactivex.Single

interface ChannelsRepository {

    fun getChannels(): Single<List<Channels>>

    fun getNewEpisodes(): Single<List<NewEpisode>>

    fun getCategories(): Single<List<String>>
}