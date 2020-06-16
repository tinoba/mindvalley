package com.tinoba.mindvalleychannels.ui.home.fragment.viewmodel

import androidx.lifecycle.MutableLiveData
import com.tinoba.mindvalleychannels.ui.home.fragment.HomeScreenModel

interface HomeFragmentViewModel {

    val uiModel: MutableLiveData<List<HomeScreenModel>>

    fun getChannels()
}