package com.tinoba.mindvalleychannels.utils

import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

interface ActivityUtils {

    fun addFragmentToActivity(@NonNull fragmentManager: FragmentManager, @NonNull fragment: Fragment, frameId: Int)

    fun addFragmentWithTagToActivity(@NonNull fragmentManager: FragmentManager, @NonNull fragment: Fragment, frameId: Int, tag: String)

    fun replaceFragmentWithTagToActivity(
        @NonNull fragmentManager: FragmentManager,
        @NonNull fragment: Fragment,
        frameId: Int,
        tag: String
    )

    fun replaceFragmentWithTagAndAnimationToActivity(
        @NonNull fragmentManager: FragmentManager,
        @NonNull fragment: Fragment,
        frameId: Int,
        tag: String,
        animateEntrance: Boolean
    )

    fun replaceFragmentWithTagAndBackStackToActivity(
        @NonNull fragmentManager: FragmentManager, @NonNull fragment: Fragment, frameId: Int,
        tag: String
    )

    fun replaceFragmentWithTagBackStackAndAnimationToActivity(
        @NonNull fragmentManager: FragmentManager,
        @NonNull fragment: Fragment,
        frameId: Int,
        tag: String,
        animateEntrance: Boolean
    )

    fun addFragmentWithTagAndBackStackToActivity(
        @NonNull fragmentManager: FragmentManager,
        @NonNull fragment: Fragment,
        frameId: Int,
        @NonNull tag: String
    )

    fun addFragmentWithTagAndBackStackToActivity(
        @NonNull fragmentManager: FragmentManager,
        @NonNull fragment: Fragment,
        frameId: Int,
        @NonNull tag: String,
        backStackName: String
    )

    fun addFragmentWithTagToActivityWithAnimation(
        @NonNull fragmentManager: FragmentManager, @NonNull fragment: Fragment, tag: String,
        frameId: Int,
        backStackName: String
    )

    fun setFragmentWithTagToActivity(
        @NonNull fragmentManager: FragmentManager, @NonNull fragment: Fragment, tag: String,
        frameId: Int,
        backStackName: String
    )

    fun setFragmentWithTagToActivityWithAnimation(
        @NonNull fragmentManager: FragmentManager, @NonNull fragment: Fragment, tag: String, frameId: Int, backStackName: String,
        animateEntrance: Boolean
    )

    fun removeFragmentFromActivity(@NonNull fragmentManager: FragmentManager, @NonNull fragment: Fragment)

    fun getCurrentFragment(fragmentManager: FragmentManager): Fragment?

}