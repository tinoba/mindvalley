package com.tinoba.domain.repository

import io.reactivex.rxjava3.core.Single

interface ChannelsRepository {

    fun getChannels(): Single<Any>
}