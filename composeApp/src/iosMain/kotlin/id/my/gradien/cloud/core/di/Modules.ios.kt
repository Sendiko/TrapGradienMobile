package id.my.gradien.cloud.core.di

import id.my.gradien.cloud.core.session.DATASTORE_FILE_NAME
import id.my.gradien.cloud.core.session.createDataStore
import io.ktor.client.engine.darwin.*
import org.koin.core.module.Module
import org.koin.dsl.module
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask
import kotlinx.cinterop.ExperimentalForeignApi

actual val platformModules: Module
    get() = module {
        single { Darwin.create() }
        @OptIn(ExperimentalForeignApi::class)
        single {
            createDataStore(
                producePath = {
                    val documentDirectory = NSFileManager.defaultManager.URLForDirectory(
                        directory = NSDocumentDirectory,
                        inDomain = NSUserDomainMask,
                        appropriateForURL = null,
                        create = false,
                        error = null
                    )
                    requireNotNull(documentDirectory).path + "/$DATASTORE_FILE_NAME"
                }
            )
        }
    }
