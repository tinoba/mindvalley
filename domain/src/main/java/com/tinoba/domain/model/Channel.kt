package com.tinoba.domain.model

data class Channel(val title: String, val coverAsset: String, val type: ChannelType)

enum class ChannelType{

    COURSE,
    SERIES
}