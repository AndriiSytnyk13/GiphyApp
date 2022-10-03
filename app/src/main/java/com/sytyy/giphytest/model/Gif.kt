package com.sytyy.giphytest.model

import com.google.gson.annotations.SerializedName
import javax.inject.Inject

data class Gif @Inject constructor(
    @SerializedName("images")
    val image: ImageGif
)