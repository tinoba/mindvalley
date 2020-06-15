package com.tinoba.mindvalleychannels.di.application.component

import com.tinoba.mindvalleychannels.di.activity.module.DataModule
import com.tinoba.mindvalleychannels.di.activity.module.MapperModule
import com.tinoba.mindvalleychannels.di.activity.module.UtilsModule
import com.tinoba.mindvalleychannels.di.application.module.ApplicationModule
import com.tinoba.mindvalleychannels.di.application.module.ThreadingModule

interface ApplicationComponentExposes : ApplicationModule.Exposes,
    ThreadingModule.Exposes,
    DataModule.Exposes,
    UtilsModule.Exposes,
    MapperModule.Exposes/*,
    UseCaseModule.Exposes,
    UtilsModule.Exposes,*/