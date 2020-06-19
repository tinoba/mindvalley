package com.tinoba.data.networking.model.reqsponse.newepisodes

import com.google.gson.annotations.SerializedName

data class NewEpisodesDataApi(@SerializedName("media") val episodes : List<NewEpisodeApi>?)