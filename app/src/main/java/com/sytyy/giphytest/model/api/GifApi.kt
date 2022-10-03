package com.sytyy.giphytest.model.api

import com.sytyy.giphytest.model.GifArrayData
import retrofit2.http.GET
import retrofit2.http.Query

interface GifApi {

    @GET("trending?api_key=D1EFYG0lxQ6iLXJO0YwLnRRo1UaqYozH&limit=25&rating=g")
    suspend fun getGifs(): GifArrayData

    @GET("search")
    suspend fun searchGifs(
        @Query("api_key") key: String = "D1EFYG0lxQ6iLXJO0YwLnRRo1UaqYozH",
        @Query("q") phrase: String,
        @Query("limit") limit: Int = 25,
        @Query("offset") offset: Int = 0,
        @Query("rating") rating: String = "g",
        @Query("lang") lang: String = "en",
    ): GifArrayData
}