package com.tinoba.mindvalleychannels.ui.home

import android.os.Bundle
import com.tinoba.mindvalleychannels.R
import com.tinoba.mindvalleychannels.base.BaseActivity
import com.tinoba.mindvalleychannels.di.activity.component.ActivityComponent
import javax.inject.Inject

class HomeActivity : BaseActivity() {

    @Inject
    lateinit var router: HomeRouter

    override fun inject(activityComponent: ActivityComponent) = activityComponent.inject(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        if (savedInstanceState == null) {
            router.showHomeScreen()
        }
    }
}