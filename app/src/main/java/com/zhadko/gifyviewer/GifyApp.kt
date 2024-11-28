package com.zhadko.gifyviewer

import android.app.Application
import com.zhadko.gifyviewer.di.dataModule
import com.zhadko.gifyviewer.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class GifyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@GifyApp)
            modules(listOf(dataModule, viewModelModule))
        }
    }
}