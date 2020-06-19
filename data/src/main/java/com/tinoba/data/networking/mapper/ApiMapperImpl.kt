package com.tinoba.data.networking.mapper

import com.tinoba.data.networking.model.reqsponse.categories.CategoriesApi
import com.tinoba.data.networking.model.reqsponse.channels.ChannelsApi
import com.tinoba.data.networking.model.reqsponse.newepisodes.NewEpisodesApi
import com.tinoba.domain.model.Channel
import com.tinoba.domain.model.ChannelType
import com.tinoba.domain.model.Channels
import com.tinoba.domain.model.NewEpisode

class ApiMapperImpl : ApiMapper {

    override fun mapToChannels(channelsApi: ChannelsApi?): List<Channels> =

        channelsApi?.data?.channels?.map { channel ->
            val channels = if (channel.series?.isEmpty() != false) {
                channel.courses?.map { course -> Channel(course.title, course.coverAsset.url ?: "", ChannelType.COURSE) }
            } else {
                channel.series.map { series -> Channel(series.title, series.coverAsset.url ?: "", ChannelType.SERIES) }
            }

            Channels(
                channel.title ?: "",
                channels?.take(6) ?: emptyList(),
                channel.mediaCount ?: 0,
                channel.id ?: 0,
                channel.iconAsset?.thumbnailUrl ?: "",
                channel.coverAsset?.url ?: ""
            )
        } ?: emptyList()

    override fun mapToNewEpisodes(newEpisodesApi: NewEpisodesApi?): List<NewEpisode> =
        newEpisodesApi?.data?.episodes?.map { NewEpisode(it.title ?: "", it.coverAsset?.url ?: "", it.channel?.title ?: "") } ?: emptyList()

    override fun mapToCategories(categoriesApi: CategoriesApi?): List<String> =
        categoriesApi?.data?.categories?.map { it.categoryName ?: "" } ?: emptyList()
}