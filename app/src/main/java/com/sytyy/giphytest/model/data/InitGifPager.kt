package com.sytyy.giphytest.model.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sytyy.giphytest.model.Gif
import com.sytyy.giphytest.model.api.GifApi

class InitGifPager(
    private val gifApi: GifApi
) : PagingSource<Int, Gif>() {

    private val START_PAGE = 1

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Gif> {
        val page = params.key ?: START_PAGE
        val data = gifApi.getGifs(offset = 1).data
        return try {
            LoadResult.Page(
                data = data,
                prevKey = if (page == START_PAGE) null else page - 1,
                nextKey = if (data.isEmpty()) null else page + 1
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
