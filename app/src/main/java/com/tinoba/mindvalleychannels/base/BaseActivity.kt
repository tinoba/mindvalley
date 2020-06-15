package com.tinoba.mindvalleychannels.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tinoba.mindvalleychannels.application.MindvalleyApplication
import com.tinoba.mindvalleychannels.di.ComponentFactory
import com.tinoba.mindvalleychannels.di.activity.component.ActivityComponent

abstract class BaseActivity : AppCompatActivity() {

    private var activityComponent: ActivityComponent? = null

    private fun getMindvalleyApplication() = MindvalleyApplication.from(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject(getActivityComponent())
    }

    fun getActivityComponent(): ActivityComponent {
        if (activityComponent == null) {
            activityComponent = ComponentFactory.createActivityComponent(this, getMindvalleyApplication().getApplicationComponent())
        }

        return activityComponent as ActivityComponent
    }

    protected abstract fun inject(activityComponent: ActivityComponent)
}
