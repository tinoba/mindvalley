package com.tinoba.mindvalleychannels.utils

import androidx.annotation.NonNull
import androidx.annotation.Size
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.tinoba.mindvalleychannels.base.BaseActivity

class ActivityUtilsImpl(val activity: BaseActivity) : ActivityUtils {

    private var screenSize: Size? = null

    override fun addFragmentToActivity(@NonNull fragmentManager: FragmentManager, @NonNull fragment: Fragment, frameId: Int) {
        if (!fragment.isAdded) {
            val transaction = fragmentManager.beginTransaction()
            transaction.add(frameId, fragment)
            transaction.commit()
        }
    }

    override fun addFragmentWithTagToActivity(
        @NonNull fragmentManager: FragmentManager,
        @NonNull fragment: Fragment,
        frameId: Int,
        @NonNull tag: String
    ) {
        if (!fragment.isAdded) {
            val transaction = fragmentManager.beginTransaction()
            transaction.add(frameId, fragment, tag)
            transaction.commit()
        }
    }

    override fun addFragmentWithTagAndBackStackToActivity(
        @NonNull fragmentManager: FragmentManager,
        @NonNull fragment: Fragment,
        frameId: Int,
        @NonNull tag: String
    ) {
        if (!fragment.isAdded) {
            val transaction = fragmentManager.beginTransaction()
            transaction.add(frameId, fragment, tag)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }

    override fun addFragmentWithTagAndBackStackToActivity(
        fragmentManager: FragmentManager,
        fragment: Fragment,
        frameId: Int,
        tag: String,
        backStackName: String
    ) {
        if (!fragment.isAdded) {
            val transaction = fragmentManager.beginTransaction()
            transaction.add(frameId, fragment, tag)
            transaction.addToBackStack(backStackName)
            transaction.commit()
        }
    }

    override fun replaceFragmentWithTagToActivity(
        @NonNull fragmentManager: FragmentManager,
        @NonNull fragment: Fragment,
        frameId: Int,
        @NonNull tag: String
    ) {
        if (!fragment.isAdded) {
            val transaction = fragmentManager.beginTransaction()
            transaction.replace(frameId, fragment, tag)
            transaction.commit()
        }
    }

    override fun replaceFragmentWithTagAndAnimationToActivity(
        @NonNull fragmentManager: FragmentManager,
        @NonNull fragment: Fragment,
        frameId: Int,
        @NonNull tag: String,
        animateEntrance: Boolean
    ) {
        if (!fragment.isAdded) {
            fragmentManager.beginTransaction().apply {
                replace(frameId, fragment, tag)
                getDefaultAnimation(this, animateEntrance)
                commit()
            }
        }
    }

    private fun getDefaultAnimation(fragmentTransaction: FragmentTransaction, animateEntrance: Boolean) {}
    /*   fragmentTransaction.setCustomAnimations(
           if (animateEntrance) DEFAULT_ENTER_TRANSITION else NO_TRANSITION,
           DEFAULT_EXIT_TRANSITION,
           NO_TRANSITION,
           DEFAULT_EXIT_TRANSITION
       )*/

    override fun replaceFragmentWithTagAndBackStackToActivity(fragmentManager: FragmentManager, fragment: Fragment, frameId: Int, tag: String) {
        if (!fragment.isAdded) {
            fragmentManager.beginTransaction().apply {
                replace(frameId, fragment, tag)
                addToBackStack(null)
                commit()
            }
        }
    }

    override fun replaceFragmentWithTagBackStackAndAnimationToActivity(
        fragmentManager: FragmentManager,
        fragment: Fragment,
        frameId: Int,
        tag: String,
        animateEntrance: Boolean
    ) {
        if (!fragment.isAdded) {
            fragmentManager.beginTransaction().apply {
                getDefaultAnimation(this, animateEntrance)
                replace(frameId, fragment, tag)
                addToBackStack(null)
                commit()
            }
        }
    }

    override fun addFragmentWithTagToActivityWithAnimation(
        @NonNull fragmentManager: FragmentManager,
        @NonNull fragment: Fragment,
        tag: String,
        frameId: Int,
        backStackName: String
    ) {
        /*  val transaction = fragmentManager.beginTransaction()
          transaction.setCustomAnimations(DEFAULT_ENTER_TRANSITION, DEFAULT_EXIT_TRANSITION, NO_TRANSITION, DEFAULT_EXIT_TRANSITION)
          transaction.add(frameId, fragment, tag)
          transaction.addToBackStack(backStackName)
          transaction.commit()*/
    }

    override fun setFragmentWithTagToActivity(
        @NonNull fragmentManager: FragmentManager,
        @NonNull fragment: Fragment,
        tag: String,
        frameId: Int,
        backStackName: String
    ) {
        setFragmentWithTagToActivityWithAnimation(fragmentManager, fragment, tag, frameId, backStackName, true)
    }

    override fun setFragmentWithTagToActivityWithAnimation(
        @NonNull fragmentManager: FragmentManager,
        @NonNull fragment: Fragment,
        tag: String,
        frameId: Int,
        backStackName: String,
        animateEntrance: Boolean
    ) {
        /* val transaction = fragmentManager.beginTransaction()
         transaction.setCustomAnimations(
             if (animateEntrance) DEFAULT_ENTER_TRANSITION else NO_TRANSITION,
             DEFAULT_EXIT_TRANSITION,
             NO_TRANSITION,
             DEFAULT_EXIT_TRANSITION
         )
         transaction.replace(frameId, fragment, tag)
         transaction.addToBackStack(backStackName)
         transaction.commit()*/
    }

    override fun removeFragmentFromActivity(@NonNull fragmentManager: FragmentManager, @NonNull fragment: Fragment) {
        val transaction = fragmentManager.beginTransaction()
        transaction.remove(fragment)
        transaction.commit()
    }

    override fun getCurrentFragment(fragmentManager: FragmentManager): Fragment? {
        val fragmentTag = fragmentManager.getBackStackEntryAt(fragmentManager.backStackEntryCount.dec()).name
        return fragmentManager.findFragmentByTag(fragmentTag)
    }
}