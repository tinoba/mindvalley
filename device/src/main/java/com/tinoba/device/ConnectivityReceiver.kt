package com.tinoba.device

import io.reactivex.Single

interface ConnectivityReceiver {

    fun isConnected(): Single<Boolean>
}