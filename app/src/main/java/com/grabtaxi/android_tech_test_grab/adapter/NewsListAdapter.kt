package com.grabtaxi.android_tech_test_grab.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.android.example.github.ui.common.DataBoundListAdapter
import com.grabtaxi.android_tech_test_grab.AppExecutors
import com.grabtaxi.android_tech_test_grab.databinding.NewsItemBinding
import com.grabtaxi.android_tech_test_grab.model.NewsItems

class NewsListAdapter(
    appExecutors: AppExecutors,
    private val repoClickCallback: ((NewsItems) -> Unit)?
) : DataBoundListAdapter<NewsItems, NewsItemBinding>(
    appExecutors = appExecutors,
    diffCallback = object : DiffUtil.ItemCallback<NewsItems>() {
        override fun areItemsTheSame(oldItem: NewsItems, newItem: NewsItems): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: NewsItems, newItem: NewsItems): Boolean {
            return oldItem.description == newItem.description
        }
    }
) {

    override fun createBinding(parent: ViewGroup): NewsItemBinding {
        val binding = NewsItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        binding.root.setOnClickListener {
            binding.newsItem?.let {
                repoClickCallback?.invoke(it)
            }
        }
        return binding
    }

    override fun bind(binding: NewsItemBinding, item: NewsItems) {
        binding.newsItem = item
    }
}
