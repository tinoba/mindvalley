package com.tinoba.mindvalleychannels.di.activity.module

import android.content.Context
import android.content.res.Resources
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

    interface Exposes {
        fun resourceUtils(): ResourceUtils
    }
}