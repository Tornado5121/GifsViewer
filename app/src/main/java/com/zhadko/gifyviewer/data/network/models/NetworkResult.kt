package com.zhadko.gifyviewer.data.network.models

sealed class NetworkResult {
    data class Success(val data: GifResponse) : NetworkResult()
    data class Error(val pair: Pair<String, String>) : NetworkResult()
}
