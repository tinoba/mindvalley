package com.tinoba.data.networking.service

import com.tinoba.domain.model.Channels
import com.tinoba.domain.model.NewEpisode
import io.reactivex.rxjava3.core.Single

interface MindvalleyClient {

    fun getChannels(): Single<List<Channels>>

    fun getNewEpisodes(): Single<List<NewEpisode>>

    fun getCategories(): Single<List<String>>
}