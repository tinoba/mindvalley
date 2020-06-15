package com.tinoba.mindvalleychannels.ui.home.fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tinoba.domain.repository.ChannelsRepository
import io.reactivex.rxjava3.core.Scheduler

class HomeFragmentViewModelFactory(
    private val backgroundThreadScheduler: Scheduler,
    private val mainThreadScheduler: Scheduler,
    private val channelsRepository: ChannelsRepository //,
    //    connectivityReceiver: ConnectivityReceiver
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeFragmentViewModel::class.java)) {
            return HomeFragmentViewModel(
                backgroundThreadScheduler,
                mainThreadScheduler,
                channelsRepository/*,
                connectivityReceiver*/
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}