package com.tinoba.mindvalleychannels.di.activity.module

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.tinoba.data.database.MindvalleyDatabase
import com.tinoba.data.networking.mapper.ApiMapper
import com.tinoba.data.networking.service.MindvalleyClient
import com.tinoba.data.networking.service.MindvalleyClientImpl
import com.tinoba.data.networking.service.MindvalleyService
import com.tinoba.data.repository.ChannelsRepositoryImpl
import com.tinoba.domain.repository.ChannelsRepository
import com.tinoba.mindvalleychannels.BuildConfig
import com.tinoba.mindvalleychannels.di.application.component.ApplicationScope
import dagger.Module
import dagger.Provides
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class DataModule {

    companion object {

        const val BASE_URL = "https://pastebin.com/raw/"
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    internal fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        return interceptor
    }

    @Provides
    @Singleton
    internal fun provideOkhttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
        val builder = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            builder.interceptors().add(interceptor)
            builder.connectTimeout(10, TimeUnit.SECONDS)
            builder.writeTimeout(10, TimeUnit.SECONDS)
            builder.readTimeout(10, TimeUnit.SECONDS)
        }

        return builder.build()
    }

    @Provides
    @Singleton
    internal fun provideMindvalleyService(retrofit: Retrofit): MindvalleyService = retrofit.create(MindvalleyService::class.java)

    @Provides
    @Singleton
    internal fun provideMindvalleyClient(mindvalleyService: MindvalleyService, apiMapper: ApiMapper): MindvalleyClient =
        MindvalleyClientImpl(mindvalleyService, apiMapper)

    @Provides
    @Singleton
    internal fun provideChannelsRepository(mindvalleyClient: MindvalleyClient): ChannelsRepository = ChannelsRepositoryImpl(mindvalleyClient)

    @Provides
    @Singleton
    fun provideMindvalleyDatabase(@ApplicationScope context: Context): MindvalleyDatabase =
        Room.databaseBuilder(context, MindvalleyDatabase::class.java, MindvalleyDatabase.NAME).build()

    @Provides
    @Singleton
    internal fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }

    interface Exposes {

        fun retrofit(): Retrofit
        fun mindvalleyService(): MindvalleyService
        fun mindvalleyDatabase(): MindvalleyDatabase
        fun mindvalleyClient(): MindvalleyClient
        fun channelsRepository(): ChannelsRepository
    }
}