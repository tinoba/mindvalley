package com.tinoba.data.repository

import com.tinoba.data.networking.service.MindvalleyClient
import com.tinoba.domain.repository.ChannelsRepository
import io.reactivex.rxjava3.core.Single

class ChannelsRepositoryImpl(private val mindvalleyClient: MindvalleyClient) : ChannelsRepository {

    override fun getChannels(): Single<Any> = mindvalleyClient.getChannels()
}