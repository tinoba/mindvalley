package com.tinoba.mindvalleychannels.di.fragment.module

import androidx.lifecycle.ViewModelProvider
import com.tinoba.mindvalleychannels.base.BaseFragment
import com.tinoba.mindvalleychannels.di.application.module.ThreadingModule
import com.tinoba.mindvalleychannels.di.fragment.component.FragmentScope
import com.tinoba.mindvalleychannels.ui.home.fragment.HomeFragmentViewModel
import com.tinoba.mindvalleychannels.ui.home.fragment.HomeFragmentViewModelFactory
import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.core.Scheduler
import javax.inject.Named

@Module
class FragmentViewModelModule(val fragment: BaseFragment) {

    @Provides
    @FragmentScope
    internal fun provideHomeFragmentViewModel(
        @Named(ThreadingModule.MAIN_SCHEDULER) mainThreadScheduler: Scheduler,
        @Named(ThreadingModule.BACKGROUND_SCHEDULER) backgroundThreadScheduler: Scheduler
    ): HomeFragmentViewModel {

        val viewModelFactory = HomeFragmentViewModelFactory(
            backgroundThreadScheduler,
            mainThreadScheduler/*,
            connectivityReceiver*/
        )

        return ViewModelProvider(fragment, viewModelFactory).get(HomeFragmentViewModel::class.java)
    }
}