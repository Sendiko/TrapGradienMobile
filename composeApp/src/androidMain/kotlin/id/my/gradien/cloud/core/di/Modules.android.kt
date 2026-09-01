package id.my.gradien.cloud.core.di

import id.my.gradien.cloud.core.session.DATASTORE_FILE_NAME
import id.my.gradien.cloud.core.session.createDataStore
import io.ktor.client.engine.okhttp.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModules: Module
    get() = module {
        single { OkHttp.create() }
        single {
            createDataStore(
                producePath = {
                    androidContext().filesDir.resolve(DATASTORE_FILE_NAME).absolutePath
                }
            )
        }
    }
