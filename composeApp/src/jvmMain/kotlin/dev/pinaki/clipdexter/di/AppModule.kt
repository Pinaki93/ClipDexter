package dev.pinaki.clipdexter.di

import dev.pinaki.clipdexter.PythonExecutor
import dev.pinaki.clipdexter.services.ClipboardService
import dev.pinaki.clipdexter.services.ClipboardServiceImpl
import dev.pinaki.clipdexter.viewmodels.MainViewModel
import org.koin.dsl.module

val appModule = module {
    // Singleton dependencies
    single { PythonExecutor() }
    single<ClipboardService> { ClipboardServiceImpl() }
    
    // Factory dependencies (new instance each time)
    // factory { SomeService() }
    
    // ViewModels
    factory { MainViewModel(get()) }
} 