package com.zhadko.gifyviewer.domain.models

import com.zhadko.gifyviewer.data.network.models.Data

sealed class GifsListResult {
    data object EmptyResult : GifsListResult()
    data class NotEmptyResult(val gifsList: List<Data>) : GifsListResult()
    data class Error(val pair: Pair<String, String>) : GifsListResult()
}