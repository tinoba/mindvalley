package com.tinoba.data.repository

import com.tinoba.data.database.crudder.CategoriesCrudder
import com.tinoba.data.database.crudder.ChannelsCrudder
import com.tinoba.data.database.crudder.NewEpisodesCrudder
import com.tinoba.data.networking.service.MindvalleyClient
import com.tinoba.device.ConnectivityReceiver
import com.tinoba.domain.model.Channels
import com.tinoba.domain.model.NewEpisode
import com.tinoba.domain.repository.ChannelsRepository
import io.reactivex.Completable
import io.reactivex.Single

class ChannelsRepositoryImpl(
    private val mindvalleyClient: MindvalleyClient,
    private val categoriesCrudder: CategoriesCrudder,
    private val newEpisodesCrudder: NewEpisodesCrudder,
    private val channelsCrudder: ChannelsCrudder,
    private val connectivityReceiver: ConnectivityReceiver
) : ChannelsRepository {

    override fun getChannels(): Single<List<Channels>> = connectivityReceiver.isConnected()
        .flatMap {
            if (it) {
                getChannelsFromNetworkAndPersist()
            } else {
                channelsCrudder.getChannels()
            }
        }

    override fun getNewEpisodes(): Single<List<NewEpisode>> = connectivityReceiver.isConnected()
        .flatMap {
            if (it) {
                getNewEpisodesFromNetworkAndPersist()
            } else {
                newEpisodesCrudder.getNewEpisodes()
            }
        }

    override fun getCategories(): Single<List<String>> = connectivityReceiver.isConnected()
        .flatMap {
            if (it) {
                getCategoriesFromNetworkAndPersist()
            } else {
                categoriesCrudder.getCategories()
            }
        }

    private fun getNewEpisodesFromNetworkAndPersist(): Single<List<NewEpisode>> {
        return mindvalleyClient.getNewEpisodes()
            .flatMap { newEpisodes ->
                saveNewEpisodes(newEpisodes)
                    .toSingleDefault(newEpisodes)
            }
    }

    private fun getChannelsFromNetworkAndPersist(): Single<List<Channels>> {
        return mindvalleyClient.getChannels()
            .flatMap { channels ->
                saveChannels(channels)
                    .toSingleDefault(channels)
            }
    }

    private fun saveChannels(categories: List<Channels>): Completable {
        return Completable.fromAction { categories.forEach { channelsCrudder.saveChannel(it) } }
    }

    private fun saveNewEpisodes(categories: List<NewEpisode>): Completable {
        return Completable.fromAction { categories.forEach { newEpisodesCrudder.saveNewEpisode(it) } }
    }

    private fun getCategoriesFromNetworkAndPersist(): Single<List<String>> {
        return mindvalleyClient.getCategories()
            .flatMap { categories ->
                saveCategories(categories)
                    .toSingleDefault(categories)
            }
    }

    private fun saveCategories(categories: List<String>): Completable {
        return Completable.fromAction { categories.forEach { categoriesCrudder.saveCategory(it) } }
    }
}
