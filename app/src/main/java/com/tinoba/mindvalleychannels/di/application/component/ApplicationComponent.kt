package com.tinoba.mindvalleychannels.di.application.component

import com.tinoba.mindvalleychannels.application.MindvalleyApplication
import com.tinoba.mindvalleychannels.di.activity.module.DataModule
import com.tinoba.mindvalleychannels.di.activity.module.MapperModule
import com.tinoba.mindvalleychannels.di.activity.module.UtilsModule
import com.tinoba.mindvalleychannels.di.application.module.ApplicationModule
import com.tinoba.mindvalleychannels.di.application.module.ThreadingModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        ThreadingModule::class,
        UtilsModule::class,
        DataModule::class,
        MapperModule::class/*,
        UseCaseModule::class,
        UtilsModule::class,*/
    ]
)
interface ApplicationComponent : ApplicationComponentInjects, ApplicationComponentExposes {

    object Initializer {

        fun init(application: MindvalleyApplication): ApplicationComponent =
            DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(application))
                .threadingModule(ThreadingModule())
                .utilsModule(UtilsModule())
                .dataModule(DataModule())
                .mapperModule(MapperModule())/*
                .utilsModule(UtilsModule())*/
                .build()
    }
}