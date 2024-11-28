package com.zhadko.gifyviewer.features.gifsList

import androidx.lifecycle.viewModelScope
import com.zhadko.gifyviewer.base.BaseViewModel
import com.zhadko.gifyviewer.data.network.models.asGifList
import com.zhadko.gifyviewer.domain.models.Gif
import com.zhadko.gifyviewer.domain.models.GifsListResult
import com.zhadko.gifyviewer.domain.repository.IGifsRepository
import kotlinx.coroutines.launch

class GifsListViewModel(
    private val gifRepository: IGifsRepository,
) : BaseViewModel<GifListStates>() {

    private var gifsList = listOf<Gif>()

    init {
        getGifsList()
    }

    override fun initialState(): GifListStates = GifListStates.Loading

    fun getGifsList() {
        viewModelScope.launch {
            updateState(GifListStates.Loading)
            updateState(
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
            )
        }
    }

}