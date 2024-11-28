package com.zhadko.gifyviewer.di

import com.bumptech.glide.Glide
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val fragmentModule = module {
    factory { Glide.with(androidContext()) }
}