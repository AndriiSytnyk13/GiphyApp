package com.sytyy.giphytest.model

import com.google.gson.annotations.SerializedName
import javax.inject.Inject

data class GifArrayData @Inject constructor(
    @SerializedName("data")
    val data: ArrayList<Gif>)