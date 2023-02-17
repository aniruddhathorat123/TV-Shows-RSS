package com.example.tvshows.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tvshows.RssEntity
import com.example.tvshows.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

private var _binding: FragmentHomeBinding? = null
  // This property is only valid between onCreateView and
  // onDestroyView.
  private val binding get() = _binding!!
    private var homeListAdapter: HomeListAdapter? = null
    private var homeRecycleView: RecyclerView? = null
    private var homeViewModel: HomeViewModel? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        setupAdapter()
        val newsObserver: Observer<List<RssEntity?>?> =
            Observer<List<RssEntity?>?> { rssEntities ->
                println(rssEntities[0]?.rssSource)
            }

        homeViewModel?.getAllAvailableRss()?.observe(this, newsObserver);
    }

    fun setupAdapter() {
        //homeRecycleView.layoutManager(LinearLayoutManager(context))
        homeRecycleView?.setLayoutManager(LinearLayoutManager(context))
        val dividerItemDecoration =
            DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        homeListAdapter = HomeListAdapter(requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

