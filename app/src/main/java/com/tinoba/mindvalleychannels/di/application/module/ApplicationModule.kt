package com.tinoba.mindvalleychannels.di.application.module

import android.content.Context
import android.content.res.Resources
import com.tinoba.mindvalleychannels.application.MindvalleyApplication
import com.tinoba.mindvalleychannels.di.application.component.ApplicationScope
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: MindvalleyApplication) {

    @Provides
    @Singleton
    internal fun provideMindvalleyApplication(): MindvalleyApplication = application

    @Provides
    @Singleton
    @ApplicationScope
    internal fun provideContext(): Context = application

    @Provides
    @Singleton
    @ApplicationScope
    internal fun provideResources(): Resources = application.resources

    interface Exposes {

        fun mindvalleyApplication(): MindvalleyApplication

        @ApplicationScope
        fun context(): Context

        @ApplicationScope
        fun resources(): Resources
    }
}