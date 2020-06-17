package com.tinoba.data.networking.mapper

import com.tinoba.data.networking.model.reqsponse.categories.CategoriesApi
import com.tinoba.data.networking.model.reqsponse.channels.ChannelsApi
import com.tinoba.data.networking.model.reqsponse.newepisodes.NewEpisodesApi
import com.tinoba.domain.model.Channels
import com.tinoba.domain.model.Course
import com.tinoba.domain.model.NewEpisode
import com.tinoba.domain.model.Series

class ApiMapperImpl : ApiMapper {

    override fun mapToChannels(channelsApi: ChannelsApi): List<Channels> = with(channelsApi) {

        return data.channels.map { channel ->
            val channels = if (channel.series.isEmpty()) {
                channel.courses.map { course -> Course(course.title, course.coverAsset.url) }
            } else {
                channel.series.map { series -> Series(series.title, series.coverAsset.url) }
            }

            Channels(channel.title, channels, channel.mediaCount, channel.id, channel.iconAsset?.thumbnailUrl ?: "", channel.coverAsset?.url ?: "")
        }
    }

    override fun mapToNewEpisodes(newEpisodesApi: NewEpisodesApi): List<NewEpisode> =
        newEpisodesApi.data.episodes.map { NewEpisode(it.title, it.coverAsset.url, it.channel.title) }

    override fun mapToCategories(categoriesApi: CategoriesApi): List<String> = categoriesApi.data.categories.map { it.categoryName }
}