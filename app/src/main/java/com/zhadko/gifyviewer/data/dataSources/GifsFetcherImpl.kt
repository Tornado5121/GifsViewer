package com.zhadko.gifyviewer.data.dataSources

import com.zhadko.gifyviewer.BuildConfig
import com.zhadko.gifyviewer.data.network.GifsApi
import com.zhadko.gifyviewer.data.network.models.NetworkResult

const val LIMIT = 10

class GifsFetcherImpl(
    private val api: GifsApi
) : GifsFetcher {

    private var offset = 0

    override suspend fun getGifsFromRemote(): NetworkResult {
        val response = try {
            api.getGifsResponse(BuildConfig.API_KEY, offset, LIMIT)
        } catch (e: Exception) {
            return NetworkResult.Error("Something happen, try again, please" to e.message.toString())
        }
        offset += LIMIT
        val data =
            response.body()
                ?: return NetworkResult.Error(
                    "Something happen, try again, please" to
                            "Something happen to getting body response"
                )

        return NetworkResult.Success(data)
    }
}
