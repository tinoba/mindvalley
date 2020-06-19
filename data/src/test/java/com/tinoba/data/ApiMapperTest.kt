package com.tinoba.data

import com.tinoba.data.networking.mapper.ApiMapperImpl
import com.tinoba.data.networking.model.reqsponse.CoverAssetApi
import com.tinoba.data.networking.model.reqsponse.categories.CategoriesApi
import com.tinoba.data.networking.model.reqsponse.categories.CategoriesDataApi
import com.tinoba.data.networking.model.reqsponse.categories.CategoryApi
import com.tinoba.data.networking.model.reqsponse.channels.ChannelApi
import com.tinoba.data.networking.model.reqsponse.channels.ChannelsApi
import com.tinoba.data.networking.model.reqsponse.channels.ChannelsDataApi
import com.tinoba.data.networking.model.reqsponse.channels.IconAssetApi
import com.tinoba.data.networking.model.reqsponse.newepisodes.NewEpisodeApi
import com.tinoba.data.networking.model.reqsponse.newepisodes.NewEpisodesApi
import com.tinoba.data.networking.model.reqsponse.newepisodes.NewEpisodesChannelApi
import com.tinoba.data.networking.model.reqsponse.newepisodes.NewEpisodesDataApi
import org.junit.Test

import org.junit.Assert.*

class ApiMapperTest {

    private val apiMapper = ApiMapperImpl()

    @Test
    fun null_values_categories_test() {

        assertNotNull(apiMapper.mapToCategories(null))
        assertNotNull(apiMapper.mapToCategories(CategoriesApi(null)))
        assertNotNull(apiMapper.mapToCategories(CategoriesApi(CategoriesDataApi(null))))
        assertNotNull(apiMapper.mapToCategories(CategoriesApi(CategoriesDataApi(listOf(CategoryApi(null))))))
    }

    @Test
    fun null_values_channels_test() {
        assertNotNull(apiMapper.mapToChannels(null))
        assertNotNull(apiMapper.mapToChannels(ChannelsApi(null)))
        assertNotNull(apiMapper.mapToChannels(ChannelsApi(ChannelsDataApi(null))))
        assertNotNull(apiMapper.mapToChannels(ChannelsApi(ChannelsDataApi(listOf(ChannelApi(null, null, null, null, null, null, null))))))
        assertNotNull(
            apiMapper.mapToChannels(
                ChannelsApi(
                    ChannelsDataApi(
                        listOf(
                            ChannelApi(
                                null,
                                null,
                                null,
                                null,
                                null,
                                IconAssetApi(null),
                                CoverAssetApi(null)
                            )
                        )
                    )
                )
            )
        )
    }

    @Test
    fun null_values_new_episodes_test() {
        assertNotNull(apiMapper.mapToNewEpisodes(null))
        assertNotNull(apiMapper.mapToNewEpisodes(NewEpisodesApi(null)))
        assertNotNull(apiMapper.mapToNewEpisodes(NewEpisodesApi(NewEpisodesDataApi(null))))
        assertNotNull(apiMapper.mapToNewEpisodes(NewEpisodesApi(NewEpisodesDataApi(listOf(NewEpisodeApi(null, null, null, null))))))
        assertNotNull(apiMapper.mapToNewEpisodes(NewEpisodesApi(NewEpisodesDataApi(listOf(NewEpisodeApi(null, null, CoverAssetApi(null), NewEpisodesChannelApi(null)))))))
    }
}