package com.zhadko.gifyviewer.features.gifsList

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.zhadko.gifyviewer.domain.models.Gif
import com.zhadko.gifyviewer.domain.repository.GifsRepository

private const val GITHUB_STARTING_PAGE_INDEX = 0
const val NETWORK_PAGE_SIZE = 10

class GifsListPagingDataSource(
    private val gifsRepository: GifsRepository,
) : PagingSource<Int, Gif>() {

    override fun getRefreshKey(state: PagingState<Int, Gif>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Gif> {
        val position = params.key ?: GITHUB_STARTING_PAGE_INDEX
        return try {
            val response = gifsRepository.getGifsList(
                offset = position,
                limit = NETWORK_PAGE_SIZE
            )
            val nextKey = if (response.isEmpty()) {
                null
            } else {
                position + params.loadSize
            }
            LoadResult.Page(
                data = response,
                prevKey = null,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
