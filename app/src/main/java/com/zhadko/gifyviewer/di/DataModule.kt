package com.zhadko.gifyviewer.di

import com.zhadko.gifyviewer.BuildConfig
import com.zhadko.gifyviewer.data.network.GifsApi
import com.zhadko.gifyviewer.data.repository.GifsRepositoryImpl
import com.zhadko.gifyviewer.domain.repository.IGifsRepository
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {
    single {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.BASE_BACKEND_URL)
            .build().create(GifsApi::class.java)
    }

    factory<IGifsRepository> { GifsRepositoryImpl(get()) }
}