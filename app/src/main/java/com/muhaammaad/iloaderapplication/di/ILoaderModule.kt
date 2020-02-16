package com.muhaammaad.iloaderapplication.di

import com.muhaammaad.iloader.base.ILoader
import org.koin.dsl.module

/**
 * Responsible to inject [ILoader] by calling [Inject]
 * @see org.koin.core.KoinComponent
 */

val iLoaderModule = module {
    single { ILoader }
}
