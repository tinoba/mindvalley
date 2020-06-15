package com.tinoba.mindvalleychannels.ui.home

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.tinoba.mindvalleychannels.R
import com.tinoba.mindvalleychannels.base.BaseRouterImpl
import com.tinoba.mindvalleychannels.ui.home.fragment.HomeFragment
import com.tinoba.mindvalleychannels.utils.ActivityUtils

private const val CONTENT_CONTAINER = R.id.homeFragmentContainer

class HomeRouterImpl(
    private val activityUtils: ActivityUtils,
    private val activity: AppCompatActivity,
    private val fragmentManager: FragmentManager
) : HomeRouter, BaseRouterImpl(activity, fragmentManager) {

    override fun showHomeScreen() {
        activityUtils.replaceFragmentWithTagToActivity(
            fragmentManager,
            HomeFragment.newInstance(),
            CONTENT_CONTAINER,
            HomeFragment.TAG
        )
    }
}