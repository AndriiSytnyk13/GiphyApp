package com.sytyy.giphytest.view.gif

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sytyy.giphytest.databinding.ItemGifFullBinding
import com.sytyy.giphytest.model.Gif
import com.sytyy.giphytest.model.util.GifComparator


class GiphyGifFullAdapter(private val context: Context) :
    PagingDataAdapter<Gif, GiphyGifFullAdapter.GifViewHolder>(GifComparator) {

    class GifViewHolder(val binding: ItemGifFullBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifViewHolder =
        GifViewHolder(
            ItemGifFullBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: GifViewHolder, position: Int) {
        val gif = getItem(position)?.image?.downsizedMedium?.url
        Glide.with(context).load(gif).into(holder.binding.gif)
    }

}
