package com.tinoba.mindvalleychannels.ui.home.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.tinoba.mindvalleychannels.R
import com.tinoba.mindvalleychannels.ui.view.CircularProgressBar
import com.tinoba.mindvalleychannels.utils.ResourceUtils
import kotlinx.android.synthetic.main.new_eppisodes_item.view.*

class NewEpisodesAdapter(
    private val layoutInflater: LayoutInflater,
    private val resourceUtils: ResourceUtils
) : ListAdapter<NewEpisodesScreenModel, NewEpisodesAdapter.NewEpisodesViewHolder>(NewEpisodesDiffUtilCallback()) {

    companion object {
        private const val LAYOUT_RESOURCE = R.layout.new_eppisodes_item
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewEpisodesViewHolder =
        NewEpisodesViewHolder(resourceUtils, layoutInflater.inflate(LAYOUT_RESOURCE, parent, false))

    override fun onBindViewHolder(holder: NewEpisodesViewHolder, position: Int) {
        holder.setItem(getItem(position))
    }

    class NewEpisodesViewHolder(
        private val resourceUtils: ResourceUtils,
        private val view: View
    ) : RecyclerView.ViewHolder(view) {

        fun setItem(screenModel: NewEpisodesScreenModel) = with(view) {
            newEpisodeName.text = screenModel.name
            newEpisodeSection.text = screenModel.section

            Glide.with(this)
                .load(screenModel.imageUrl)
                .fitCenter()
                .placeholder(CircularProgressBar(context))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.drawable.no_image)
                .dontAnimate()
                .into(newEpisodeImage)
        }
    }
}

class NewEpisodesDiffUtilCallback : DiffUtil.ItemCallback<NewEpisodesScreenModel>() {

    override fun areItemsTheSame(oldItem: NewEpisodesScreenModel, newItem: NewEpisodesScreenModel): Boolean = oldItem === newItem

    override fun areContentsTheSame(oldItem: NewEpisodesScreenModel, newItem: NewEpisodesScreenModel): Boolean = oldItem == newItem
}