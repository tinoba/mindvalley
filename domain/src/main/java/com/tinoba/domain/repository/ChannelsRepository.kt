package com.tinoba.domain.repository

import com.tinoba.domain.model.Channels
import io.reactivex.rxjava3.core.Single

interface ChannelsRepository {

    fun getChannels(): Single<List<Channels>>
}