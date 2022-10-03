package com.sytyy.giphytest.model

import javax.inject.Inject

data class DownsizedGif @Inject constructor(
    val url: String
)