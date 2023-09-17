package com.zhadko.gifyviewer.data.repository

import com.zhadko.gifyviewer.data.dataSources.GifsFetcher
import com.zhadko.gifyviewer.data.network.models.NetworkResult
import com.zhadko.gifyviewer.domain.iRepository.IGifsRepository
import com.zhadko.gifyviewer.domain.models.GifsListResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GifsRepositoryImpl(
    private val gifsFetcher: GifsFetcher
) : IGifsRepository {

    override suspend fun getGifsList(): GifsListResult {
        return withContext(Dispatchers.IO) {
            when (val result = gifsFetcher.getGifsFromRemote()) {
                is NetworkResult.Error -> GifsListResult.Error(result.pair)
                is NetworkResult.Success -> {
                    if (result.data.data.isNotEmpty()) {
                        GifsListResult.NotEmptyResult(result.data.data)
                    } else {
                        GifsListResult.EmptyResult
                    }
                }

                null -> GifsListResult.Error(
                    "Something went wrong, try again, please" to
                            "Something happen on GifsRepository "
                )
            }
        }
    }
}