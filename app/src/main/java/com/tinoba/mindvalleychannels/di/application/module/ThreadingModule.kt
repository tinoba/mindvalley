package com.tinoba.mindvalleychannels.di.application.module

import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers

import javax.inject.Named
import javax.inject.Singleton

@Module
class ThreadingModule {

    companion object {

        const val MAIN_SCHEDULER: String = "main_scheduler"

        const val BACKGROUND_SCHEDULER: String = "background_scheduler"
    }

    @Provides
    @Singleton
    @Named(MAIN_SCHEDULER)
    fun provideMainScheduler(): Scheduler = AndroidSchedulers.mainThread()

    @Provides
    @Singleton
    @Named(BACKGROUND_SCHEDULER)
    fun provideBackgroundScheduler(): Scheduler = Schedulers.io()

    interface Exposes {

        @Named(ThreadingModule.MAIN_SCHEDULER)
        fun mainScheduler(): Scheduler

        @Named(ThreadingModule.BACKGROUND_SCHEDULER)
        fun backgroundScheduler(): Scheduler
    }
}