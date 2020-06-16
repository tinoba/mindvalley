package com.tinoba.data.networking.service

import com.tinoba.domain.model.Channels
import io.reactivex.rxjava3.core.Single

interface MindvalleyClient {

    fun getChannels(): Single<List<Channels>>
}