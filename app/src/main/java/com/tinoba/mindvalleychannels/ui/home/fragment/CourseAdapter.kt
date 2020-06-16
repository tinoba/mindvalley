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
import kotlinx.android.synthetic.main.course_item.view.*

class CourseAdapter(
    private val layoutInflater: LayoutInflater,
    private val resourceUtils: ResourceUtils
) : ListAdapter<SeriesScreenModel, CourseAdapter.CourseViewHolder>(CourseDiffUtilCallback()) {

    companion object {
        private const val LAYOUT_RESOURCE = R.layout.course_item
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder =
        CourseViewHolder(resourceUtils, layoutInflater.inflate(LAYOUT_RESOURCE, parent, false))

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        holder.setItem(getItem(position))
    }

    class CourseViewHolder(
        private val resourceUtils: ResourceUtils,
        private val view: View
    ) : RecyclerView.ViewHolder(view) {

        fun setItem(screenModel: SeriesScreenModel) = with(view) {
            coursesName.text = screenModel.name

            Glide.with(this)
                .load(screenModel.imageUrl)
                .placeholder(CircularProgressBar(context))
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.drawable.ic_mindvalley_placeholder)
                .into(courseImage)
        }
    }
}

class CourseDiffUtilCallback : DiffUtil.ItemCallback<SeriesScreenModel>() {

    override fun areItemsTheSame(oldItem: SeriesScreenModel, newItem: SeriesScreenModel): Boolean = oldItem === newItem

    override fun areContentsTheSame(oldItem: SeriesScreenModel, newItem: SeriesScreenModel): Boolean = oldItem == newItem
}