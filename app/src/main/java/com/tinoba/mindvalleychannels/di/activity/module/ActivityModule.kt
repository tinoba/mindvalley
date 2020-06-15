package com.tinoba.mindvalleychannels.di.activity.module

import android.app.Activity
import android.view.LayoutInflater
import androidx.fragment.app.FragmentManager
import com.tinoba.mindvalleychannels.base.BaseActivity
import com.tinoba.mindvalleychannels.di.activity.component.ActivityScope
import com.tinoba.mindvalleychannels.utils.ActivityUtils
import com.tinoba.mindvalleychannels.utils.ActivityUtilsImpl
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: BaseActivity) {

    @Provides
    @ActivityScope
    internal fun provideActivity(): Activity {
        return activity
    }

    @Provides
    @ActivityScope
    internal fun provideFragmentManager(): FragmentManager {
        return activity.supportFragmentManager
    }

    @Provides
    @ActivityScope
    internal fun provideLayoutInflater(): LayoutInflater {
        return LayoutInflater.from(activity)
    }

    @Provides
    @ActivityScope
    fun provideActivityUtils(): ActivityUtils = ActivityUtilsImpl(activity)

    interface Exposes {

        fun activity(): Activity

        fun fragmentManager(): FragmentManager

        fun layoutInflater(): LayoutInflater

        fun activityUtil(): ActivityUtils
    }
}