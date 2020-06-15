package com.tinoba.mindvalleychannels.ui.home.fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.reactivex.rxjava3.core.Scheduler

class HomeFragmentViewModelFactory(
    private val backgroundThreadScheduler: Scheduler,
    private val mainThreadScheduler: Scheduler //,
    //    connectivityReceiver: ConnectivityReceiver
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeFragmentViewModel::class.java)) {
            return HomeFragmentViewModel(
                backgroundThreadScheduler,
                mainThreadScheduler/*,
                connectivityReceiver*/
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}