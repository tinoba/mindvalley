package com.tinoba.mindvalleychannels.di

import com.tinoba.mindvalleychannels.application.MindvalleyApplication
import com.tinoba.mindvalleychannels.base.BaseActivity
import com.tinoba.mindvalleychannels.base.BaseFragment
import com.tinoba.mindvalleychannels.di.activity.component.ActivityComponent
import com.tinoba.mindvalleychannels.di.application.component.ApplicationComponent
import com.tinoba.mindvalleychannels.di.fragment.component.FragmentComponent

object ComponentFactory {

    fun createApplicationComponent(application: MindvalleyApplication): ApplicationComponent =
        ApplicationComponent.Initializer.init(application)

    fun createActivityComponent(daggerActivity: BaseActivity, applicationComponent: ApplicationComponent): ActivityComponent =
        ActivityComponent.Initializer.init(daggerActivity, applicationComponent)

    fun createFragmentComponent(daggerFragment: BaseFragment, activityComponent: ActivityComponent): FragmentComponent =
        FragmentComponent.Initializer.init(daggerFragment, activityComponent)
}