package com.zhadko.gifyviewer.features.gifsList

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.zhadko.gifyviewer.base.BaseViewModel
import com.zhadko.gifyviewer.domain.repository.IGifsRepository

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
}
