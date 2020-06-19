package com.tinoba.data.database.crudder

import com.tinoba.data.database.dao.ChannelsDao
import com.tinoba.data.database.model.DbChannels
import com.tinoba.domain.model.Channels
import io.reactivex.Completable
import io.reactivex.Single

class ChannelsCrudderImpl(private val channelsDao: ChannelsDao) : ChannelsCrudder {

    override fun saveChannel(channels: Channels) = with(channels) {
        channelsDao.saveChannel(DbChannels(title, id, channels.channels, mediaCount, iconAsset, coverAsset))
    }

    override fun getChannels(): Single<List<Channels>> =
        channelsDao.getChannels().map { it.map { Channels(it.title, it.channels, it.count, it.id, it.iconAsset, it.coverAsset) } }

    override fun clearChannels(): Completable = channelsDao.clearChannels()
}