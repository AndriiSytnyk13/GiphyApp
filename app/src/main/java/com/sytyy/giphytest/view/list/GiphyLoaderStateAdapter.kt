package com.sytyy.giphytest.view.list

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewParent
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sytyy.giphytest.R
import com.sytyy.giphytest.databinding.ItemProgressBinding

class GiphyLoaderStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<GiphyLoaderStateAdapter.LoadStateViewHolder>() {

    class LoadStateViewHolder(val binding: ItemProgressBinding, retry: () -> Unit) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.retryButton.setOnClickListener {
                retry.invoke()
            }
        }

        fun bind(loadState: LoadState) {
            binding.apply {
                progressBar.isVisible = loadState is LoadState.Loading
                retryButton.isVisible = loadState !is LoadState.Loading
            }
        }

        companion object {
            fun create(parent: ViewGroup, retry: () -> Unit): LoadStateViewHolder =
                LoadStateViewHolder(
                    ItemProgressBinding.bind(
                        LayoutInflater.from(parent.context)
                            .inflate(R.layout.item_progress, parent, false)
                    ), retry
                )
        }
    }

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) =
        holder.bind(loadState)


    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder =
        LoadStateViewHolder.create(parent, retry)

}