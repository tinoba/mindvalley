package com.tinoba.mindvalleychannels.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.tinoba.mindvalleychannels.di.ComponentFactory
import com.tinoba.mindvalleychannels.di.fragment.component.FragmentComponent

abstract class BaseFragment : Fragment() {

    private var fragmentComponent: FragmentComponent? = null

    fun getBaseActivity() = activity as BaseActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject(getFragmentComponent())
    }

    fun getFragmentComponent(): FragmentComponent {
        if (fragmentComponent == null) {
            fragmentComponent = ComponentFactory.createFragmentComponent(this, getBaseActivity().getActivityComponent())
        }

        return fragmentComponent as FragmentComponent
    }

    protected abstract fun inject(fragmentComponent: FragmentComponent)
}