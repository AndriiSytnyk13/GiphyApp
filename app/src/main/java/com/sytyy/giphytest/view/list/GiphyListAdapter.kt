package com.sytyy.giphytest.view.list

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.sytyy.giphytest.databinding.ItemGifBinding
import com.sytyy.giphytest.model.Gif


class GiphyListAdapter(private val context: Context, private val list: ArrayList<Gif>) :
    RecyclerView.Adapter<GiphyListAdapter.GifViewHolder>() {

    class GifViewHolder(val binding: ItemGifBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifViewHolder =
        GifViewHolder(
            ItemGifBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: GifViewHolder, position: Int) {
        val gif = list[position].image.downsizedMedium.url
        Glide.with(context)
            .load(gif)
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .into(holder.binding.gifFrame)
        holder.binding.gifFrame.setOnClickListener {

            Navigation.findNavController(holder.binding.root).navigate(
                GiphyListFragmentDirections.actionGiphyListFragmentToGiphyGifFragment(
                    position)
            )
        }
    }

    override fun getItemCount(): Int = list.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateGifs(gifList: ArrayList<Gif>) {
        list.clear()
        list.addAll(gifList)
        notifyDataSetChanged()
    }
}
