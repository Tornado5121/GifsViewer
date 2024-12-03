package com.zhadko.gifyviewer.data.repository

import com.zhadko.gifyviewer.data.network.GifsApi
import com.zhadko.gifyviewer.data.network.models.asGifList
import com.zhadko.gifyviewer.domain.models.Gif
import com.zhadko.gifyviewer.domain.repository.IGifsRepository

class GifsRepositoryImpl(
    private val gifsApi: GifsApi,
) : IGifsRepository {

    override suspend fun getGifsList(
        offset: Int,
        limit: Int,
    ): List<Gif> {
        val response = gifsApi.getGifsResponse(offset = offset, limit = limit)
        return if (response.isSuccessful) {
            response.body()?.data?.asGifList() ?: emptyList()
        } else {
            throw Exception()
        }
    }
}