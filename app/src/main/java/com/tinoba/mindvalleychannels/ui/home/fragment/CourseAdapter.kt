package com.tinoba.mindvalleychannels.ui.home.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tinoba.mindvalleychannels.R
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
        holder.setItem(getItem(position), position, itemCount)
    }

    class CourseViewHolder(
        private val resourceUtils: ResourceUtils,
        private val view: View
    ) : RecyclerView.ViewHolder(view) {

        fun setItem(screenModel: SeriesScreenModel, position: Int, itemCount: Int) = with(view) {
            coursesName.text = screenModel.name
        }
    }
}

class CourseDiffUtilCallback : DiffUtil.ItemCallback<SeriesScreenModel>() {

    override fun areItemsTheSame(oldItem: SeriesScreenModel, newItem: SeriesScreenModel): Boolean = oldItem === newItem

    override fun areContentsTheSame(oldItem: SeriesScreenModel, newItem: SeriesScreenModel): Boolean = oldItem == newItem
}