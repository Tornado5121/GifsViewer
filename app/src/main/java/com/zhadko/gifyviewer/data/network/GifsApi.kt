package com.zhadko.gifyviewer.data.network

import com.zhadko.gifyviewer.BuildConfig
import com.zhadko.gifyviewer.data.network.models.GifResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GifsApi {

    @GET("v1/gifs/trending")
    suspend fun getGifsResponse(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
    ): Response<GifResponse>
}