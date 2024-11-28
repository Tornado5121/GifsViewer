package com.zhadko.gifyviewer.di

import com.bumptech.glide.Glide
import com.zhadko.gifyviewer.domain.models.Gif
import com.zhadko.gifyviewer.features.gifsList.GifsAdapter
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val fragmentModule = module {
    factory { Glide.with(androidContext()) }
    factory { (onClick: (Gif) -> Unit) -> GifsAdapter(onClick) }
}