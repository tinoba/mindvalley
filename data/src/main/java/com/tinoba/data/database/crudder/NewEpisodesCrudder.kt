package com.tinoba.data.database.crudder

import com.tinoba.domain.model.NewEpisode
import io.reactivex.Completable
import io.reactivex.Single

interface NewEpisodesCrudder {

    fun saveNewEpisode(newEpisode: NewEpisode)

    fun getNewEpisodes(): Single<List<NewEpisode>>

    fun clearNewEpisodes(): Completable
}