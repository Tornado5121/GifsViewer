package com.zhadko.gifyviewer.features.gifsList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zhadko.gifyviewer.data.network.models.asGifList
import com.zhadko.gifyviewer.domain.repository.IGifsRepository
import com.zhadko.gifyviewer.domain.models.Gif
import com.zhadko.gifyviewer.domain.models.GifsListResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GifsListViewModel(
    private val gifRepository: IGifsRepository
) : ViewModel() {

    private val _state = MutableStateFlow<GifListStates>(GifListStates.Loading)
    val state = _state.asStateFlow()

    private var gifsList = listOf<Gif>()

    init {
        getGifsList()
    }

    fun getGifsList() {
        viewModelScope.launch {
            _state.update { GifListStates.Loading }
            _state.update {
                when (val gifsResult = gifRepository.getGifsList()) {
                    GifsListResult.EmptyResult -> {
                        GifListStates.EmptyGifsList
                    }

                    is GifsListResult.Error -> GifListStates.Error(gifsResult.pair)
                    is GifsListResult.NotEmptyResult -> {
                        gifsList += gifsResult.gifsList.asGifList()
                        GifListStates.GifsList(
                            gifsList
                        )
                    }
                }
            }
        }
    }

}