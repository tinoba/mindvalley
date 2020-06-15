package com.tinoba.data.networking.service

import io.reactivex.rxjava3.core.Single

interface MindvalleyClient {

    fun getChannels(): Single<Any>
}