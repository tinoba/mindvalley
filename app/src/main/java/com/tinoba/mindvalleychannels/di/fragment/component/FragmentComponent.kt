package com.tinoba.mindvalleychannels.di.fragment.component

import com.tinoba.mindvalleychannels.base.BaseFragment
import com.tinoba.mindvalleychannels.di.activity.component.ActivityComponent
import com.tinoba.mindvalleychannels.di.activity.component.ActivityComponentExposes
import com.tinoba.mindvalleychannels.di.fragment.module.FragmentModule
import com.tinoba.mindvalleychannels.di.fragment.module.FragmentViewModelModule
import dagger.Component

@FragmentScope
@Component(
    dependencies = [ActivityComponent::class],
    modules = [
        FragmentModule::class,
        FragmentViewModelModule::class
    ]
)
interface FragmentComponent : FragmentComponentInjects,
    FragmentComponentExposes,
    ActivityComponentExposes {

    object Initializer {

        fun init(fragment: BaseFragment, activityComponent: ActivityComponent): FragmentComponent =
            DaggerFragmentComponent.builder()
                .activityComponent(activityComponent)
                .fragmentModule(FragmentModule(fragment))
                .fragmentViewModelModule(FragmentViewModelModule(fragment))
                .build()
    }
}