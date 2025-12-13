package id.my.gradien.cloud.core.di

import io.ktor.client.engine.darwin.*
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModules: Module
    get() = module {
        single { Darwin.create() }
    }