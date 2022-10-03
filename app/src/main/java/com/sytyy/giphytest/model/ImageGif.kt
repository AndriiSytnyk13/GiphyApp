package com.sytyy.giphytest.model

import com.google.gson.annotations.SerializedName
import javax.inject.Inject

data class ImageGif @Inject constructor(
    @SerializedName("downsized_medium")
    val downsizedMedium: DownsizedGif)
