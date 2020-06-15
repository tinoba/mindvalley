package com.tinoba.mindvalleychannels.ui.home.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.tinoba.mindvalleychannels.R
import com.tinoba.mindvalleychannels.base.BaseFragment
import com.tinoba.mindvalleychannels.di.fragment.component.FragmentComponent
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

        adapter.submitList(
            listOf(
                HomeScreenModel.TitleItem(),
                HomeScreenModel.NewEpisodesItem(1, listOf(
                    NewEpisodesScreenModel(1, "The Cure For Loneliness", "mindvalley mentoring", ""),
                    NewEpisodesScreenModel(2, "The Cure For Loneliness 1", "mindvalley mentoring", ""),
                    NewEpisodesScreenModel(3, "The Cure For Loneliness 2", "mindvalley mentoring", "")
                )),
                HomeScreenModel.SeriesItem(1, "Mindvalley On Stage", 20, listOf(
                    SeriesScreenModel(1, "A-Fest Sardinia 2018", ""),
                    SeriesScreenModel(2, "A-Fest Sardinia 2019", "")
                )),
                HomeScreenModel.CourseItem(1, "Impact At Work", 15, listOf(
                    SeriesScreenModel(1, "The Female Perspective", ""),
                    SeriesScreenModel(1, "Feuled By Courage", ""),
                    SeriesScreenModel(1, "Feuled By Courage 2", "")
                )),

                HomeScreenModel.CourseItem(1, "Impact At Work 2", 15, listOf(
                    SeriesScreenModel(1, "The Female Perspective", ""),
                    SeriesScreenModel(1, "Feuled By Courage", ""),
                    SeriesScreenModel(1, "Feuled By Courage 2", "")
                )),
                HomeScreenModel.CategoriesItem(
                    1, listOf(
                        "Health & Fitness",
                        "Love Relationship",
                        "Emotional"
                    )
                )
            )
        )

        Toast.makeText(context, viewModel.getNumber().toString(), Toast.LENGTH_SHORT).show()
    }

    private fun initAdapter() {
        adapter = ChannelsAdapter(inflater, resourceUtils)

        channelsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
//        val itemDecor = DividerDecorator(requireContext())
//        channelsRecyclerView.addItemDecoration(itemDecor)
        channelsRecyclerView.adapter = adapter
    }
}