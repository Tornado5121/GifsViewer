package com.zhadko.gifyviewer.domain.repository

import com.zhadko.gifyviewer.domain.models.GifsListResult

interface IGifsRepository {

    suspend fun getGifsList(): GifsListResult
}