package com.tinoba.data.networking.service

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface MindvalleyService {

    companion object {

        private const val CHANNELS_ENDPOINT = "Xt12uVhM"
    }

    @GET(CHANNELS_ENDPOINT)
    fun getChannels(): Single<Any>

}