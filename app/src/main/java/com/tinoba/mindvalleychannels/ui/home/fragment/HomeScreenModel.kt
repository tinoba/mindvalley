package com.tinoba.mindvalleychannels.ui.home.fragment

sealed class HomeScreenModel(val type: Int) {

    class TitleItem : HomeScreenModel(TYPE) {

        companion object {

            const val TYPE = 1
        }
    }

    data class NewEpisodesItem(val id: Int, val newEpisodes: List<NewEpisodesScreenModel>) : HomeScreenModel(TYPE) {

        companion object {

            const val TYPE = 2
        }
    }

    data class SeriesItem(val id: Int, val title: String, val amount: Int, val series: List<SeriesScreenModel>) : HomeScreenModel(TYPE) {

        companion object {

            const val TYPE = 3
        }
    }

    data class CourseItem(val id: Int, val title: String, val amount: Int, val series: List<SeriesScreenModel>) : HomeScreenModel(TYPE) {

        companion object {

            const val TYPE = 4
        }
    }

    data class CategoriesItem(val id: Int, val categories: List<String>) : HomeScreenModel(TYPE) {

        companion object {

            const val TYPE = 5
        }
    }
}