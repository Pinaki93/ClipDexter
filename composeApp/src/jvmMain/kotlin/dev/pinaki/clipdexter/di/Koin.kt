package dev.pinaki.clipdexter.di

import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

object KoinHelper {
    
    fun initKoin() {
        startKoin {
            modules(appModule)
        }
    }
    
    fun stopKoin() {
        stopKoin()
    }
} 