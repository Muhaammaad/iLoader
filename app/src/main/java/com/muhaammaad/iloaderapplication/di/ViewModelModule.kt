package com.muhaammaad.iloaderapplication.di

import com.muhaammaad.iloaderapplication.ui.main.viewmodel.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Responsible to inject [MainViewModel] by calling [viewModel]
 */

val viewModelModule = module {
    viewModel { MainViewModel(get(), get()) }
}
