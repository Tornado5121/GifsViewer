package com.zhadko.gifyviewer.di

import com.zhadko.gifyviewer.features.gifsList.GifsListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { GifsListViewModel(get()) }
}