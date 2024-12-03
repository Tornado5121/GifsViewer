package com.zhadko.gifyviewer.features.gifsList

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.zhadko.gifyviewer.base.BaseViewModel
import com.zhadko.gifyviewer.domain.repository.IGifsRepository
import kotlinx.coroutines.launch

class GifsListViewModel(
    private val gifRepository: IGifsRepository,
) : BaseViewModel<GifListStates>() {

    override fun initialState(): GifListStates = GifListStates.Loading

    val items = Pager(
        config = PagingConfig(
            pageSize = NETWORK_PAGE_SIZE,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { GifsListPagingDataSource(gifsRepository = gifRepository) }
    ).flow.cachedIn(viewModelScope)

//    fun getGifsList() {
//        viewModelScope.launch {
//            updateState(GifListStates.Loading)
//            gifRepository.getGifsList().cachedIn(viewModelScope).collect { result ->
//                updateState(GifListStates.GifsList(result))
//            }
////            updateState(
////                when (val gifsResult = gifRepository.getGifsList()) {
////                    GifsListResult.EmptyResult -> {
////                        GifListStates.EmptyGifsList
////                    }
////
////                    is GifsListResult.Error -> GifListStates.Error(gifsResult.pair)
////                    is GifsListResult.NotEmptyResult -> {
////                        gifsList += gifsResult.gifsList.asGifList()
////                        GifListStates.GifsList(
////                            gifsList
////                        )
////                    }
////                }
////            )
//        }
//    }

}