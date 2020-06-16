package com.tinoba.domain.model

data class Channels(
    val title: String,
    val channels: List<Channel>,
    val mediaCount: Int,
    val id: Int,
    val iconAsset: String,
    val coverAsset: String
)