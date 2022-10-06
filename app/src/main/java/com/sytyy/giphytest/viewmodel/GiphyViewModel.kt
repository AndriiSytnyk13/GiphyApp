package com.sytyy.giphytest.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.sytyy.giphytest.model.repository.GifRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GiphyViewModel @Inject constructor(private val repository: GifRepository) : ViewModel() {

    fun searchGifs(path: String) = repository.searchGifs(path).cachedIn(viewModelScope)

    fun getAllGifs() = repository.getGifs()
}
