package com.sytyy.giphytest.view.gif

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sytyy.giphytest.databinding.ItemGifFullBinding
import com.sytyy.giphytest.model.Gif


class GiphyGifFullAdapter(private val context: Context, private val list: ArrayList<Gif>) :
    RecyclerView.Adapter<GiphyGifFullAdapter.GifViewHolder>() {

    class GifViewHolder(val binding: ItemGifFullBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifViewHolder =
        GifViewHolder(
            ItemGifFullBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: GifViewHolder, position: Int) {
        val gif = list[position].image.downsizedMedium.url
        Glide.with(context).load(gif).into(holder.binding.gif)
    }

    override fun getItemCount(): Int = list.size


    @SuppressLint("NotifyDataSetChanged")
    fun getGifs(gifList: ArrayList<Gif>) {
        list.addAll(gifList)
        notifyDataSetChanged()
    }


}
