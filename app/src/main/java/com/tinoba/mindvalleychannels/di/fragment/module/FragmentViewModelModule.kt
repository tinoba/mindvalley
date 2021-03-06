package com.tinoba.mindvalleychannels.di.fragment.module

import androidx.lifecycle.ViewModelProvider
import com.tinoba.domain.repository.ChannelsRepository
import com.tinoba.mindvalleychannels.base.BaseFragment
import com.tinoba.mindvalleychannels.di.application.module.ThreadingModule
import com.tinoba.mindvalleychannels.di.fragment.component.FragmentScope
import com.tinoba.mindvalleychannels.ui.home.fragment.viewmodel.HomeFragmentViewModel
import com.tinoba.mindvalleychannels.ui.home.fragment.viewmodel.HomeFragmentViewModelFactory
import com.tinoba.mindvalleychannels.ui.home.fragment.viewmodel.HomeFragmentViewModelImpl
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import javax.inject.Named

@Module
class FragmentViewModelModule(val fragment: BaseFragment) {

    @Provides
    @FragmentScope
    internal fun provideHomeFragmentViewModel(
        @Named(ThreadingModule.MAIN_SCHEDULER) mainThreadScheduler: Scheduler,
        @Named(ThreadingModule.BACKGROUND_SCHEDULER) backgroundThreadScheduler: Scheduler,
        channelsRepository: ChannelsRepository
    ): HomeFragmentViewModel {

        val viewModelFactory = HomeFragmentViewModelFactory(
            backgroundThreadScheduler,
            mainThreadScheduler,
            channelsRepository
            //connectivityReceiver
        )

        return ViewModelProvider(fragment, viewModelFactory).get(HomeFragmentViewModelImpl::class.java)
    }
}