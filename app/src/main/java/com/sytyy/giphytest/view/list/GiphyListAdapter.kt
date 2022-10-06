package com.sytyy.giphytest.view.list


import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.paging.PagingData
import androidx.paging.PagingDataAdapter
import androidx.paging.map
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.sytyy.giphytest.databinding.ItemGifBinding
import com.sytyy.giphytest.model.Gif
import com.sytyy.giphytest.model.util.GifComparator


class GiphyListAdapter(private val context: Context,  val phrase: String = "init") :
    PagingDataAdapter<Gif, GiphyListAdapter.GifViewHolder>(GifComparator) {

    class GifViewHolder(val binding: ItemGifBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifViewHolder =
        GifViewHolder(
            ItemGifBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: GifViewHolder, position: Int) {
        val gif = getItem(position)?.image?.downsizedMedium?.url
        Glide.with(context)
            .load(gif)
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .into(holder.binding.gifFrame)
        holder.binding.gifFrame.setOnClickListener {
            Navigation.findNavController(holder.binding.root).navigate(
                GiphyListFragmentDirections.actionGiphyListFragmentToGiphyGifFragment(
                    position, phrase
                )
            )
            Log.d("Phrase", phrase)
        }
    }





}
