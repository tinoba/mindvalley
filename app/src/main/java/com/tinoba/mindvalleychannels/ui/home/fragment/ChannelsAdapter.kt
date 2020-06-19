package com.tinoba.mindvalleychannels.ui.home.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.*
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.tinoba.mindvalleychannels.R
import com.tinoba.mindvalleychannels.ui.view.CircularProgressBar
import com.tinoba.mindvalleychannels.ui.view.GridSpacingItemDecoration
import com.tinoba.mindvalleychannels.utils.ResourceUtils
import kotlinx.android.synthetic.main.channels_categories_item.view.*
import kotlinx.android.synthetic.main.channels_item.view.*
import kotlinx.android.synthetic.main.channels_new_episodes_item.view.*

class ChannelsAdapter(
    private val layoutInflater: LayoutInflater,
    private val resourceUtils: ResourceUtils
) : ListAdapter<HomeScreenModel, RecyclerView.ViewHolder>(ChannelDiffUtilCallback()) {

    companion object {
        private const val TITLE_LAYOUT_RESOURCE = R.layout.channels_title_item
        private const val NEW_EPISODES_LAYOUT_RESOURCE = R.layout.channels_new_episodes_item
        private const val CHANNELS_LAYOUT_RESOURCE = R.layout.channels_item
        private const val CATEGORIES_LAYOUT_RESOURCE = R.layout.channels_categories_item
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            HomeScreenModel.TitleItem.TYPE -> TitleViewHolder(layoutInflater.inflate(TITLE_LAYOUT_RESOURCE, parent, false))
            HomeScreenModel.NewEpisodesItem.TYPE -> NewEpisodesViewHolder(layoutInflater.inflate(NEW_EPISODES_LAYOUT_RESOURCE, parent, false))
            HomeScreenModel.SeriesItem.TYPE -> SeriesViewHolder(layoutInflater.inflate(CHANNELS_LAYOUT_RESOURCE, parent, false))
            HomeScreenModel.CourseItem.TYPE -> CourseViewHolder(layoutInflater.inflate(CHANNELS_LAYOUT_RESOURCE, parent, false))
            HomeScreenModel.CategoriesItem.TYPE -> CategoriesViewHolder(layoutInflater.inflate(CATEGORIES_LAYOUT_RESOURCE, parent, false))
            else -> TitleViewHolder(layoutInflater.inflate(TITLE_LAYOUT_RESOURCE, parent, false))
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val screenModel = getItem(position)

        when (holder) {
            is NewEpisodesViewHolder -> holder.setItem(screenModel as HomeScreenModel.NewEpisodesItem, resourceUtils, layoutInflater)
            is SeriesViewHolder -> holder.setItem(screenModel as HomeScreenModel.SeriesItem, resourceUtils, layoutInflater)
            is CourseViewHolder -> holder.setItem(screenModel as HomeScreenModel.CourseItem, resourceUtils, layoutInflater)
            is CategoriesViewHolder -> holder.setItem(screenModel as HomeScreenModel.CategoriesItem, resourceUtils, layoutInflater)
        }
    }

    override fun getItemViewType(position: Int): Int = getItem(position).type

    class TitleViewHolder(view: View) : RecyclerView.ViewHolder(view)

    class NewEpisodesViewHolder(
        private val view: View
    ) : RecyclerView.ViewHolder(view) {

        fun setItem(
            screenModel: HomeScreenModel.NewEpisodesItem,
            resourceUtils: ResourceUtils,
            layoutInflater: LayoutInflater
        ) = with(view) {

            val childLayoutManager = LinearLayoutManager(newEpisodesRecyclerView.context, LinearLayoutManager.HORIZONTAL, false)
            val newEpisodesAdapter = NewEpisodesAdapter(layoutInflater, resourceUtils)
            newEpisodesRecyclerView.apply {
                layoutManager = childLayoutManager
                setRecycledViewPool(RecyclerView.RecycledViewPool())

                adapter = newEpisodesAdapter
            }

            newEpisodesAdapter.submitList(screenModel.newEpisodes)
        }
    }

    class SeriesViewHolder(
        private val view: View
    ) : RecyclerView.ViewHolder(view) {

        fun setItem(
            screenModel: HomeScreenModel.SeriesItem,
            resourceUtils: ResourceUtils,
            layoutInflater: LayoutInflater
        ) = with(view) {
            seriesTitle.text = screenModel.title
            seriesSubtitle.text = resourceUtils.getString(R.string.series_amount, screenModel.amount)

            Glide.with(this)
                .load(screenModel.iconUrl)
                .fitCenter()
                .placeholder(CircularProgressBar(context))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.drawable.ic_mindvalley_placeholder)
                .dontAnimate()
                .circleCrop()
                .into(seriesIcon)

            val childLayoutManager = LinearLayoutManager(seriesRecyclerView.context, LinearLayoutManager.HORIZONTAL, false)
            val seriesAdapter = SeriesAdapter(layoutInflater, resourceUtils)
            seriesRecyclerView.apply {
                layoutManager = childLayoutManager
                setRecycledViewPool(RecyclerView.RecycledViewPool())

                adapter = seriesAdapter
            }

            seriesAdapter.submitList(screenModel.series)
        }
    }

    class CourseViewHolder(
        private val view: View
    ) : RecyclerView.ViewHolder(view) {

        fun setItem(
            screenModel: HomeScreenModel.CourseItem,
            resourceUtils: ResourceUtils,
            layoutInflater: LayoutInflater
        ) = with(view) {
            seriesTitle.text = screenModel.title
            seriesSubtitle.text = resourceUtils.getString(R.string.series_amount, screenModel.amount)

            Glide.with(this)
                .load(screenModel.iconUrl)
                .fitCenter()
                .placeholder(CircularProgressBar(context))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.drawable.ic_mindvalley_placeholder)
                .dontAnimate()
                .circleCrop()
                .into(seriesIcon)

            val childLayoutManager = LinearLayoutManager(seriesRecyclerView.context, LinearLayoutManager.HORIZONTAL, false)
            val courseAdapter = CourseAdapter(layoutInflater, resourceUtils)
            seriesRecyclerView.apply {
                layoutManager = childLayoutManager
                setRecycledViewPool(RecyclerView.RecycledViewPool())

                adapter = courseAdapter
            }

            courseAdapter.submitList(screenModel.series)
        }
    }

    class CategoriesViewHolder(
        private val view: View
    ) : RecyclerView.ViewHolder(view) {

        companion object {

            const val SPAN_COUNT = 2
        }

        fun setItem(
            screenModel: HomeScreenModel.CategoriesItem,
            resourceUtils: ResourceUtils,
            layoutInflater: LayoutInflater
        ) = with(view) {

            val childLayoutManager = GridLayoutManager(categoriesRecyclerView.context, SPAN_COUNT)
            val categoriesAdapter = CategoriesAdapter(layoutInflater, resourceUtils)
            categoriesRecyclerView.apply {
                layoutManager = childLayoutManager
                setRecycledViewPool(RecyclerView.RecycledViewPool())
                addItemDecoration(
                    GridSpacingItemDecoration(
                        SPAN_COUNT,
                        resourceUtils.getDimensionPixelSizeFromResource(R.dimen.categories_grid_view_spacing)
                    )
                )
                adapter = categoriesAdapter
            }

            categoriesAdapter.submitList(screenModel.categories)
        }
    }
}

class ChannelDiffUtilCallback : DiffUtil.ItemCallback<HomeScreenModel>() {

    override fun areItemsTheSame(oldItem: HomeScreenModel, newItem: HomeScreenModel): Boolean = oldItem === newItem

    override fun areContentsTheSame(oldItem: HomeScreenModel, newItem: HomeScreenModel): Boolean = oldItem == newItem
}