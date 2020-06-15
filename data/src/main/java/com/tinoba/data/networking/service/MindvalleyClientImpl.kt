package com.tinoba.data.networking.service

import com.tinoba.data.networking.mapper.ApiMapper
import io.reactivex.rxjava3.core.Single

class MindvalleyClientImpl(
    private val mindvalleyService: MindvalleyService,
    private val apiMapper: ApiMapper
) : MindvalleyClient {

    override fun getChannels(): Single<Any> =
        Single.defer { mindvalleyService.getChannels() }
}