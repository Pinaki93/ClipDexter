package dev.pinaki.clipdexter.di

import dev.pinaki.clipdexter.PythonExecutor
import dev.pinaki.clipdexter.PyUtilitiesRepository
import dev.pinaki.clipdexter.services.ClipboardService
import dev.pinaki.clipdexter.services.ClipboardServiceImpl
import dev.pinaki.clipdexter.services.PythonUtilitiesDataStore
import dev.pinaki.clipdexter.viewmodels.MainViewModel
import dev.pinaki.clipdexter.viewmodels.UtilitiesViewModel
import org.koin.dsl.module

val appModule = module {
    // Singleton dependencies
    single { PythonExecutor() }
    single<ClipboardService> { ClipboardServiceImpl() }
    single { PythonUtilitiesDataStore() }
    single { PyUtilitiesRepository(get()) }
    
    // Factory dependencies (new instance each time)
    // factory { SomeService() }
    
    // ViewModels
    factory { MainViewModel(get()) }
    factory { UtilitiesViewModel(get(), get(), get()) }
} 