package com.tinoba.mindvalleychannels.ui.home.fragment.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tinoba.domain.model.Channels
import com.tinoba.domain.model.Course
import com.tinoba.domain.model.Series
import com.tinoba.domain.repository.ChannelsRepository
import com.tinoba.mindvalleychannels.ui.home.fragment.HomeScreenModel
import com.tinoba.mindvalleychannels.ui.home.fragment.SeriesScreenModel
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable

class HomeFragmentViewModelImpl(
    private val backgroundThreadScheduler: Scheduler,
    private val mainThreadScheduler: Scheduler,
    private val channelsRepository: ChannelsRepository
) : HomeFragmentViewModel, ViewModel() {

    private val screenModel: MutableLiveData<List<HomeScreenModel>> by lazy { MutableLiveData<List<HomeScreenModel>>() }

    private val disposable = CompositeDisposable()

    override val uiModel: MutableLiveData<List<HomeScreenModel>>
        get() = screenModel

    override fun getChannels() {
        disposable.add(
            channelsRepository.getChannels()
                .subscribeOn(backgroundThreadScheduler)
                .map { mapToHomeScreenModels(it) }
                .observeOn(mainThreadScheduler)
                .subscribe(this::onGetChannelsSuccess, Throwable::printStackTrace)
        )
    }

    private fun mapToHomeScreenModels(channels: List<Channels>): List<HomeScreenModel> {
        val homeScreenModels = mutableListOf<HomeScreenModel>()
        homeScreenModels.add(HomeScreenModel.TitleItem())

        channels.forEach {
            if (it.channels.isNotEmpty() && it.channels.first() is Course){
                homeScreenModels.add(HomeScreenModel.CourseItem(it.id, it.title, it.mediaCount, it.channels.map { SeriesScreenModel(it.title, it.coverAsset) }))
            }else if (it.channels.isNotEmpty() && it.channels.first() is Series){
                homeScreenModels.add(HomeScreenModel.SeriesItem(it.id, it.title, it.mediaCount, it.channels.map { SeriesScreenModel(it.title, it.coverAsset) }))
            }
        }

        return homeScreenModels
    }

    private fun onGetChannelsSuccess(homeScreenModels: List<HomeScreenModel>) {
        uiModel.value = homeScreenModels
    }

    override fun onCleared() {

        disposable.clear()
        super.onCleared()
    }
}