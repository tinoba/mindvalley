package com.tinoba.mindvalleychannels.di.application.component

import com.tinoba.mindvalleychannels.di.activity.module.UtilsModule
import com.tinoba.mindvalleychannels.di.application.module.ApplicationModule
import com.tinoba.mindvalleychannels.di.application.module.ThreadingModule

interface ApplicationComponentExposes : ApplicationModule.Exposes,
    ThreadingModule.Exposes,
    UtilsModule.Exposes/*,
    MapperModule.Exposes,
    UseCaseModule.Exposes,
    DataModule.Exposes,
    UtilsModule.Exposes,*/