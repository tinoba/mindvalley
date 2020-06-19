package com.tinoba.data.database.crudder

import com.tinoba.data.database.dao.NewEpisodesDao
import com.tinoba.data.database.model.DbNewEpisodes
import com.tinoba.domain.model.NewEpisode
import io.reactivex.Completable
import io.reactivex.Single

class NewEpisodesCrudderImpl(private val newEpisodesDao: NewEpisodesDao) : NewEpisodesCrudder {

    override fun saveNewEpisode(newEpisode: NewEpisode) = with(newEpisode) {
        newEpisodesDao.saveEpisode(DbNewEpisodes(title, coverAssetUrl, channelName))
    }

    override fun getNewEpisodes(): Single<List<NewEpisode>> =
        newEpisodesDao.getNewEpisodes().map { it.map { NewEpisode(it.title, it.url, it.channel) } }

    override fun clearNewEpisodes(): Completable = newEpisodesDao.clearNewEpisodes()

}