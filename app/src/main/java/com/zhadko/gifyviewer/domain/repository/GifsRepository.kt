package com.zhadko.gifyviewer.domain.repository

import com.zhadko.gifyviewer.domain.models.Gif

interface GifsRepository {

    suspend fun getGifsList(offset: Int, limit: Int): List<Gif>
}