package com.tinoba.mindvalleychannels.ui.home.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.tinoba.mindvalleychannels.R
import com.tinoba.mindvalleychannels.base.BaseFragment
import com.tinoba.mindvalleychannels.di.fragment.component.FragmentComponent
import com.tinoba.mindvalleychannels.ui.home.fragment.viewmodel.HomeFragmentViewModel
import com.tinoba.mindvalleychannels.utils.ResourceUtils
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

class HomeFragment : BaseFragment() {

    companion object {
        val TAG: String = HomeFragment::class.java.simpleName

        fun newInstance() = HomeFragment()
    }

    @Inject
    lateinit var viewModel: HomeFragmentViewModel

    @Inject
    lateinit var inflater: LayoutInflater

    @Inject
    lateinit var resourceUtils: ResourceUtils

    override fun inject(fragmentComponent: FragmentComponent) = fragmentComponent.inject(this)

    private lateinit var adapter: ChannelsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initAdapter()

        viewModel.uiModel.observe(this, Observer { homeScreenModels ->
            adapter.submitList(homeScreenModels)
        })

        viewModel.getData()

    }

    private fun initAdapter() {
        adapter = ChannelsAdapter(inflater, resourceUtils)

        channelsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        channelsRecyclerView.adapter = adapter
    }
}