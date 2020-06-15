package com.tinoba.mindvalleychannels.di.activity.module

import com.tinoba.data.networking.mapper.ApiMapper
import com.tinoba.data.networking.mapper.ApiMapperImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MapperModule {

    @Provides
    @Singleton
    fun provideApiMapper(): ApiMapper = ApiMapperImpl()

    interface Exposes {
        fun apiMapper(): ApiMapper

    }
}