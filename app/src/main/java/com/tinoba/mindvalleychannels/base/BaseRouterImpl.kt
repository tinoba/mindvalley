package com.tinoba.mindvalleychannels.base

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager

private const val LAST_FRAGMENT = 0

abstract class BaseRouterImpl(
    private val activity: AppCompatActivity,
    private val fragmentManager: FragmentManager
) : BaseRouter {

    override fun closeScreen() {
        activity.finish()
    }

    override fun closeScreenWithOkResult() {
        activity.setResult(Activity.RESULT_OK)
        closeScreen()
    }

    override fun goBack() {
        if (fragmentManager.backStackEntryCount == LAST_FRAGMENT) {
            closeScreen()
        } else {
            fragmentManager.popBackStack()
        }
    }
}