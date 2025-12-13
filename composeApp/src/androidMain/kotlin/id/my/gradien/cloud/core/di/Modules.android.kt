package id.my.gradien.cloud.core.di

import io.ktor.client.engine.okhttp.*
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModules: Module
    get() = module {
        single { OkHttp.create() }
    }