package com.zhadko.gifyviewer.features.gifsList

import com.zhadko.gifyviewer.domain.models.Gif

sealed class GifListStates {
    data object Loading : GifListStates()
    data class GifsList(val result: List<Gif>) : GifListStates()
    data object EmptyGifsList : GifListStates()
    data class Error(val pair: Pair<String, String>) : GifListStates()
}