package com.zhadko.gifyviewer.features.gifsList

import com.zhadko.gifyviewer.base.State
import com.zhadko.gifyviewer.domain.models.Gif

sealed class GifListStates : State {
    data object Loading : GifListStates()
    data class GifsList(val result: List<Gif>) : GifListStates()
    data object EmptyGifsList : GifListStates()
    data class Error(val pair: Pair<String, String>) : GifListStates()
}