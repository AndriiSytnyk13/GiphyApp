package com.sytyy.giphytest.model.util

import androidx.recyclerview.widget.DiffUtil
import com.sytyy.giphytest.model.Gif

object GifComparator : DiffUtil.ItemCallback<Gif>() {
    override fun areItemsTheSame(oldItem: Gif, newItem: Gif): Boolean =
        oldItem.image.downsizedMedium.url == newItem.image.downsizedMedium.url

    override fun areContentsTheSame(oldItem: Gif, newItem: Gif) = oldItem == newItem
}