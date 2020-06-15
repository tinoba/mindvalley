package com.tinoba.mindvalleychannels.ui.home.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tinoba.mindvalleychannels.R
import com.tinoba.mindvalleychannels.utils.ResourceUtils
import kotlinx.android.synthetic.main.category_item.view.*

class CategoriesAdapter(
    private val layoutInflater: LayoutInflater,
    private val resourceUtils: ResourceUtils
) : ListAdapter<String, CategoriesAdapter.CategoryViewHolder>(CategoryDiffUtilCallback()) {

    companion object {
        private const val LAYOUT_RESOURCE = R.layout.category_item
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder =
        CategoryViewHolder(resourceUtils, layoutInflater.inflate(LAYOUT_RESOURCE, parent, false))

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.setItem(getItem(position), position, itemCount)
    }

    class CategoryViewHolder(
        private val resourceUtils: ResourceUtils,
        private val view: View
    ) : RecyclerView.ViewHolder(view) {

        fun setItem(category: String, position: Int, itemCount: Int) = with(view) {
            categoryName.text = category
        }
    }
}

class CategoryDiffUtilCallback : DiffUtil.ItemCallback<String>() {

    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean = oldItem === newItem

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean = oldItem == newItem
}