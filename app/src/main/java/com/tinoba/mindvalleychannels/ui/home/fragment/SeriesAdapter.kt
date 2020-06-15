package com.tinoba.mindvalleychannels.ui.home.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tinoba.mindvalleychannels.R
import com.tinoba.mindvalleychannels.utils.ResourceUtils
import kotlinx.android.synthetic.main.series_item.view.*

class SeriesAdapter(
    private val layoutInflater: LayoutInflater,
    private val resourceUtils: ResourceUtils
) : ListAdapter<SeriesScreenModel, SeriesAdapter.SeriesViewHolder>(SeriesDiffUtilCallback()) {

    companion object {
        private const val LAYOUT_RESOURCE = R.layout.series_item
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeriesViewHolder =
        SeriesViewHolder(resourceUtils, layoutInflater.inflate(LAYOUT_RESOURCE, parent, false))

    override fun onBindViewHolder(holder: SeriesViewHolder, position: Int) {
        holder.setItem(getItem(position), position, itemCount)
    }

    class SeriesViewHolder(
        private val resourceUtils: ResourceUtils,
        private val view: View
    ) : RecyclerView.ViewHolder(view) {

        fun setItem(screenModel: SeriesScreenModel, position: Int, itemCount: Int) = with(view) {
            seriesName.text = screenModel.name
        }
    }
}

class SeriesDiffUtilCallback : DiffUtil.ItemCallback<SeriesScreenModel>() {

    override fun areItemsTheSame(oldItem: SeriesScreenModel, newItem: SeriesScreenModel): Boolean = oldItem === newItem

    override fun areContentsTheSame(oldItem: SeriesScreenModel, newItem: SeriesScreenModel): Boolean = oldItem == newItem
}