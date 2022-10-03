package com.sytyy.giphytest.viewmodel

import android.icu.text.Transliterator.Position
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sytyy.giphytest.model.Gif
import com.sytyy.giphytest.model.repository.GifRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.http.Path
import javax.inject.Inject

@HiltViewModel
class GiphyViewModel @Inject constructor(private val repository: GifRepository): ViewModel() {

    private val _gifs = MutableLiveData<ArrayList<Gif>>()
    val gifs: LiveData<ArrayList<Gif>> = _gifs

    val _position = MutableLiveData<Int>()

    init {
        getAllGifs()
    }

    fun searchGifs(path: String) {
        viewModelScope.launch {
            _gifs.value = repository.searchGifs(path).data
        }
    }


    private fun getAllGifs() {
        viewModelScope.launch {
            _gifs.value = repository.getGifs().data
        }
    }


}