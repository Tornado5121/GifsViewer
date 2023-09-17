package com.zhadko.gifyviewer.data.dataSources

import com.zhadko.gifyviewer.data.network.models.NetworkResult

interface GifsFetcher {

    suspend fun getGifsFromRemote(): NetworkResult?
}