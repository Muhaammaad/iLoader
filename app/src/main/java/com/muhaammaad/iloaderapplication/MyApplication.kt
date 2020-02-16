package com.muhaammaad.iloaderapplication

import android.app.Application
import com.muhaammaad.iloaderapplication.di.iLoaderModule
import com.muhaammaad.iloaderapplication.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Start Koin
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(listOf(iLoaderModule, viewModelModule))
        }
    }
}