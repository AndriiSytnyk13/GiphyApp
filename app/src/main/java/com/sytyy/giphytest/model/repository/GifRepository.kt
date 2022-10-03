package com.sytyy.giphytest.model.repository

import com.sytyy.giphytest.model.GifArrayData
import com.sytyy.giphytest.model.api.GifApi
import javax.inject.Inject

class GifRepository @Inject constructor(private val gifApi: GifApi) {

    suspend fun getGifs(): GifArrayData = gifApi.getGifs()

    suspend fun searchGifs(phrase: String) = gifApi.searchGifs(phrase = phrase)

}