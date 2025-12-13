package id.my.gradien.cloud.core.di

import id.my.gradien.cloud.core.network.HttpClientFactory
import org.koin.core.module.Module
import org.koin.dsl.module

expect val platformModules: Module

val sharedModules = module {
    single { HttpClientFactory.create(get()) }
}