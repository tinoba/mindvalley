package com.tinoba.mindvalleychannels.ui.home.fragment

import androidx.lifecycle.ViewModel
import com.tinoba.domain.repository.ChannelsRepository
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable

class HomeFragmentViewModel(
    private val backgroundThreadScheduler: Scheduler,
    private val mainThreadScheduler: Scheduler,
    private val channelsRepository: ChannelsRepository
) : ViewModel() {

    private val disposable = CompositeDisposable()

    fun getChannels() {
        disposable.add(
            channelsRepository.getChannels()
                .subscribeOn(backgroundThreadScheduler)
                .observeOn(mainThreadScheduler)
                .subscribe(this::onGetChannelsSuccess, Throwable::printStackTrace)
        )
    }

    private fun onGetChannelsSuccess(any: Any) {

    }

    override fun onCleared() {

        disposable.clear()
        super.onCleared()
    }
}