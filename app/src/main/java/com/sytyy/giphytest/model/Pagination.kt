package com.sytyy.giphytest.model

import com.google.gson.annotations.SerializedName
import javax.inject.Inject

data class Pagination @Inject constructor(
    @SerializedName("count")
    val count: Int)