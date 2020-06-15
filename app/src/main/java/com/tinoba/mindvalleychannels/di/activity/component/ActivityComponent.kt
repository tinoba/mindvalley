package com.tinoba.mindvalleychannels.di.activity.component

import com.tinoba.mindvalleychannels.base.BaseActivity
import com.tinoba.mindvalleychannels.di.activity.module.ActivityModule
import com.tinoba.mindvalleychannels.di.activity.module.ActivityViewModelModule
import com.tinoba.mindvalleychannels.di.activity.module.RouterModule
import com.tinoba.mindvalleychannels.di.application.component.ApplicationComponent
import com.tinoba.mindvalleychannels.di.application.component.ApplicationComponentExposes
import dagger.Component

@ActivityScope
@Component(
    dependencies = [(ApplicationComponent::class)],
    modules = [
        ActivityModule::class,
        ActivityViewModelModule::class,
        RouterModule::class
    ]
)
interface ActivityComponent : ActivityComponentInjects,
    ActivityComponentExposes, ApplicationComponentExposes {

    object Initializer {

        fun init(baseActivity: BaseActivity, applicationComponent: ApplicationComponent): ActivityComponent =
            DaggerActivityComponent.builder()
                .applicationComponent(applicationComponent)
                .activityModule(ActivityModule(baseActivity))
                .activityViewModelModule(ActivityViewModelModule())
                .routerModule(RouterModule(baseActivity))
                .build()

    }
}
