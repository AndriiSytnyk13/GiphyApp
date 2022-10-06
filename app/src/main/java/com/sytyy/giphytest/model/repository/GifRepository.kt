package com.sytyy.giphytest.model.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.sytyy.giphytest.model.api.GifApi
import com.sytyy.giphytest.model.data.InitGifPager
import com.sytyy.giphytest.model.data.SearchGifPager
import javax.inject.Inject

class GifRepository @Inject constructor(private val gifApi: GifApi) {

    private val PAGE_SIZE = 2

    fun getGifs() =
        Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false,
            ),
            pagingSourceFactory = { InitGifPager(gifApi) }
        ).liveData

    fun searchGifs(phrase: String) =
        Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false,
            ),
            pagingSourceFactory = { SearchGifPager(gifApi, phrase) }
        ).liveData


}