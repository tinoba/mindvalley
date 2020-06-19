package com.tinoba.data.database.crudder

import com.tinoba.domain.model.Channels
import io.reactivex.Completable
import io.reactivex.Single

interface ChannelsCrudder {

    fun saveChannel(channels: Channels)

    fun getChannels(): Single<List<Channels>>

    fun clearChannels(): Completable
}