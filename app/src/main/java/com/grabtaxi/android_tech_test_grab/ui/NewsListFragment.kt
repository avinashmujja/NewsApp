package com.grabtaxi.android_tech_test_grab.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.grabtaxi.android_tech_test_grab.AppExecutors
import com.grabtaxi.android_tech_test_grab.Injectable
import com.grabtaxi.android_tech_test_grab.adapter.NewsListAdapter
import com.grabtaxi.android_tech_test_grab.databinding.FragmentNewsListBinding
import com.grabtaxi.android_tech_test_grab.util.Constant
import com.grabtaxi.android_tech_test_grab.viewmodel.NewsViewModel
import javax.inject.Inject

open class NewsListFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var appExecutors: AppExecutors

    lateinit var adapter : NewsListAdapter

    lateinit var binding : FragmentNewsListBinding

    lateinit var newsViewModel : NewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        newsViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(NewsViewModel::class.java)
        binding.setLifecycleOwner(viewLifecycleOwner)
        initRecyclerView()
        val nlAdapter = NewsListAdapter(
            appExecutors = appExecutors
        ) { newsItem ->
            navController().navigate(
                NewsListFragmentDirections.showDetails(newsItem.url)
            )
        }

        binding.newsList.adapter = nlAdapter
        adapter = nlAdapter

    }

    private fun initRecyclerView() {
        binding.searchResult = newsViewModel.results
        newsViewModel.setCountry(Constant.country_IN)
        newsViewModel.results.observe(viewLifecycleOwner, Observer { result ->
            adapter.submitList(result?.data)
        })
    }
    open fun navController() = findNavController()
}