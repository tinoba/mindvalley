package com.tinoba.mindvalleychannels.application

import android.app.Application
import android.content.Context
import com.tinoba.mindvalleychannels.di.ComponentFactory
import com.tinoba.mindvalleychannels.di.application.component.ApplicationComponent
import io.reactivex.plugins.RxJavaPlugins

class MindvalleyApplication : Application() {

    companion object {
        fun from(context: Context): MindvalleyApplication {
            return context.applicationContext as MindvalleyApplication
        }
    }

    private lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        RxJavaPlugins.setErrorHandler(Throwable::printStackTrace)
        applicationComponent = ComponentFactory.createApplicationComponent(this)
        applicationComponent.inject(this)
    }

    fun getApplicationComponent() = applicationComponent
}