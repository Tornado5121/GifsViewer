package com.zhadko.gifyviewer.features.gifsList

import com.zhadko.gifyviewer.base.State

sealed class GifListStates : State {
    data object Loading : GifListStates()
    data object EmptyGifsList : GifListStates()
}