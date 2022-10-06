package com.sytyy.giphytest.model.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sytyy.giphytest.model.Gif
import com.sytyy.giphytest.model.api.GifApi

class SearchGifPager(
    private val gifApi: GifApi,
    private val phrase: String
) : PagingSource<Int, Gif>() {

    private val START_PAGE = 1
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Gif> {
        return try {
            val page = params.key ?: START_PAGE
            val response = gifApi.searchGifs(phrase = phrase)
            LoadResult.Page(
                data = response.data,
                prevKey = if (page == START_PAGE) null else page - 1,
                nextKey = null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }


    override fun getRefreshKey(state: PagingState<Int, Gif>): Int? = state.anchorPosition?.let {
        state.closestPageToPosition(it)?.prevKey?.plus(1)
            ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
    }


}
