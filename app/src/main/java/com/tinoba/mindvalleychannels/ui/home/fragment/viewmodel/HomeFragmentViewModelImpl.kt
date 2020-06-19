package com.tinoba.mindvalleychannels.ui.home.fragment.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tinoba.domain.model.*
import com.tinoba.domain.repository.ChannelsRepository
import com.tinoba.mindvalleychannels.ui.home.fragment.HomeScreenModel
import com.tinoba.mindvalleychannels.ui.home.fragment.NewEpisodesScreenModel
import com.tinoba.mindvalleychannels.ui.home.fragment.SeriesScreenModel
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Function3

class HomeFragmentViewModelImpl(
    private val backgroundThreadScheduler: Scheduler,
    private val mainThreadScheduler: Scheduler,
    private val channelsRepository: ChannelsRepository
) : HomeFragmentViewModel, ViewModel() {

    private val screenModel: MutableLiveData<List<HomeScreenModel>> by lazy { MutableLiveData<List<HomeScreenModel>>() }

    private val disposable = CompositeDisposable()

    override val uiModel: MutableLiveData<List<HomeScreenModel>>
        get() = screenModel

    override fun getData() {
        disposable.add(
            Single.zip(
                channelsRepository.getChannels(),
                channelsRepository.getNewEpisodes(),
                channelsRepository.getCategories(),
                Function3 { channels: List<Channels>, newEpisodes: List<NewEpisode>, categories: List<String> ->
                    InitialData(
                        channels,
                        newEpisodes,
                        categories
                    )
                }
            )
                .map { mapToHomeScreenModels(it) }
                .observeOn(mainThreadScheduler)
                .subscribeOn(backgroundThreadScheduler)
                .subscribe(this::onGetChannelsSuccess, Throwable::printStackTrace)
        )
    }

    private fun mapToHomeScreenModels(initialData: InitialData): List<HomeScreenModel> {
        with(initialData) {
            val homeScreenModels = mutableListOf<HomeScreenModel>()
            homeScreenModels.add(HomeScreenModel.TitleItem())

            homeScreenModels.add(HomeScreenModel.NewEpisodesItem(newEpisodes.map {
                NewEpisodesScreenModel(
                    it.title,
                    it.channelName,
                    it.coverAssetUrl
                )
            }))

            channels.forEach {
                if (it.channels.all { it.type === ChannelType.COURSE }) {
                    homeScreenModels.add(
                        HomeScreenModel.CourseItem(
                            it.id,
                            it.title,
                            it.mediaCount,
                            it.iconAsset,
                            it.channels.map { SeriesScreenModel(it.title, it.coverAsset) })
                    )
                } else if (it.channels.all {  it.type === ChannelType.SERIES }) {
                    homeScreenModels.add(
                        HomeScreenModel.SeriesItem(
                            it.id,
                            it.title,
                            it.mediaCount,
                            it.iconAsset,
                            it.channels.map { SeriesScreenModel(it.title, it.coverAsset) })
                    )
                }
            }

            homeScreenModels.add(HomeScreenModel.CategoriesItem(categories))

            return homeScreenModels
        }
    }

    private fun onGetChannelsSuccess(homeScreenModels: List<HomeScreenModel>) {
        uiModel.value = homeScreenModels
    }

    override fun onCleared() {

        disposable.clear()
        super.onCleared()
    }

    data class InitialData(val channels: List<Channels>, val newEpisodes: List<NewEpisode>, val categories: List<String>)
}