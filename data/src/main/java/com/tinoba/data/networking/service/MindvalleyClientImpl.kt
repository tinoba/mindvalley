package com.tinoba.data.networking.service

import com.tinoba.data.networking.mapper.ApiMapper
import com.tinoba.domain.model.Channels
import com.tinoba.domain.model.NewEpisode
import io.reactivex.rxjava3.core.Single

class MindvalleyClientImpl(
    private val mindvalleyService: MindvalleyService,
    private val apiMapper: ApiMapper
) : MindvalleyClient {

    override fun getChannels(): Single<List<Channels>> =
        Single.defer { mindvalleyService.getChannels() }
            .map(apiMapper::mapToChannels)

    override fun getNewEpisodes(): Single<List<NewEpisode>> =
        Single.defer { mindvalleyService.getNewEpisodes() }
            .map(apiMapper::mapToNewEpisodes)
}