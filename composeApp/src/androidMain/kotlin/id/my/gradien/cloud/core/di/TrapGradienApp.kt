package id.my.gradien.cloud.core.di

import android.app.Application
import org.koin.android.ext.koin.androidContext

class TrapGradienApp: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@TrapGradienApp)
        }
    }
}