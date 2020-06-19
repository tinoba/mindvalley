package com.tinoba.mindvalleychannels.di.application.module

import android.content.Context
import android.content.res.Resources
import com.tinoba.device.ConnectivityReceiver
import com.tinoba.device.ConnectivityReceiverImpl
import com.tinoba.mindvalleychannels.utils.ResourceUtils
import com.tinoba.mindvalleychannels.utils.ResourceUtilsImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UtilsModule {

    @Provides
    @Singleton
    fun provideResourceUtils(context: Context, resources: Resources): ResourceUtils = ResourceUtilsImpl(context, resources)

    @Provides
    @Singleton
    fun provideConnectivityReceiver(context: Context): ConnectivityReceiver = ConnectivityReceiverImpl(context)

    interface Exposes {
        fun resourceUtils(): ResourceUtils
    }
}