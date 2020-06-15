package com.tinoba.mindvalleychannels.di.activity.module

import androidx.fragment.app.FragmentManager
import com.tinoba.mindvalleychannels.base.BaseActivity
import com.tinoba.mindvalleychannels.di.activity.component.ActivityScope
import com.tinoba.mindvalleychannels.ui.home.HomeRouter
import com.tinoba.mindvalleychannels.ui.home.HomeRouterImpl
import com.tinoba.mindvalleychannels.utils.ActivityUtils
import dagger.Module
import dagger.Provides

@Module
class RouterModule(private val activity: BaseActivity) {

    @Provides
    @ActivityScope
    internal fun provideHomeRouter(
        activityUtils: ActivityUtils,
        fragmentManager: FragmentManager
    ): HomeRouter {
        return HomeRouterImpl(activityUtils, activity, fragmentManager)
    }


    interface Exposes {
        fun homeRouter(): HomeRouter
    }
}