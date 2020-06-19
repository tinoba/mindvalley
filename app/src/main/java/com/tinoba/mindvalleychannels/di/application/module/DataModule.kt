package com.tinoba.mindvalleychannels.di.application.module

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.tinoba.data.database.MindvalleyDatabase
import com.tinoba.data.database.crudder.*
import com.tinoba.data.database.dao.CategoriesDao
import com.tinoba.data.database.dao.ChannelsDao
import com.tinoba.data.database.dao.NewEpisodesDao
import com.tinoba.data.networking.mapper.ApiMapper
import com.tinoba.data.networking.service.MindvalleyClient
import com.tinoba.data.networking.service.MindvalleyClientImpl
import com.tinoba.data.networking.service.MindvalleyService
import com.tinoba.data.repository.ChannelsRepositoryImpl
import com.tinoba.device.ConnectivityReceiver
import com.tinoba.domain.repository.ChannelsRepository
import com.tinoba.mindvalleychannels.BuildConfig
import com.tinoba.mindvalleychannels.di.application.component.ApplicationScope
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class DataModule {

    companion object {

        const val TIMEOUT_SECONDS = 10L

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
            builder.connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            builder.writeTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            builder.readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
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
    internal fun provideChannelsRepository(
        mindvalleyClient: MindvalleyClient,
        categoriesCrudder: CategoriesCrudder,
        connectivityReceiver: ConnectivityReceiver,
        channelsCrudder: ChannelsCrudder,
        newEpisodesCrudder: NewEpisodesCrudder
    ): ChannelsRepository = ChannelsRepositoryImpl(mindvalleyClient, categoriesCrudder, newEpisodesCrudder, channelsCrudder, connectivityReceiver)

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
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideCategoriesDao(database: MindvalleyDatabase): CategoriesDao = database.categories()

    @Provides
    @Singleton
    fun provideCategoriesCrudder(categoriesDao: CategoriesDao): CategoriesCrudder = CategoriesCruderImpl(categoriesDao)

    @Provides
    @Singleton
    fun provideNewEpisodesDao(database: MindvalleyDatabase): NewEpisodesDao = database.newEpisodes()

    @Provides
    @Singleton
    fun provideNewEpisodesCrudder(newEpisodesDao: NewEpisodesDao): NewEpisodesCrudder = NewEpisodesCrudderImpl(newEpisodesDao)

    @Provides
    @Singleton
    fun provideChannelsDao(database: MindvalleyDatabase): ChannelsDao = database.channels()

    @Provides
    @Singleton
    fun provideChannelsCrudder(channelsDao: ChannelsDao): ChannelsCrudder = ChannelsCrudderImpl(channelsDao)

    interface Exposes {

        fun retrofit(): Retrofit
        fun mindvalleyService(): MindvalleyService
        fun mindvalleyDatabase(): MindvalleyDatabase
        fun mindvalleyClient(): MindvalleyClient
        fun channelsRepository(): ChannelsRepository
    }
}