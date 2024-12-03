package com.zhadko.gifyviewer.domain.repository

import com.zhadko.gifyviewer.domain.models.Gif

interface IGifsRepository {

    suspend fun getGifsList(offset: Int, limit: Int): List<Gif>
}