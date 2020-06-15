package com.tinoba.mindvalleychannels.ui.home.fragment

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.core.Scheduler

class HomeFragmentViewModel(
    val backgroundThreadScheduler: Scheduler,
    val mainThreadScheduler: Scheduler
) : ViewModel() {

    var a = 0

    fun getNumber(): Int {
        a++
        return a
    }
}