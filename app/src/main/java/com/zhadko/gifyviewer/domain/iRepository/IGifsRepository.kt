package com.zhadko.gifyviewer.domain.iRepository

import com.zhadko.gifyviewer.domain.models.GifsListResult

interface IGifsRepository {

    suspend fun getGifsList(): GifsListResult
}