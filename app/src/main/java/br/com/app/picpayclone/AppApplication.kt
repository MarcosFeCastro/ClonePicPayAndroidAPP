package br.com.app.picpayclone

import android.app.Application
import br.com.app.picpayclone.di.serviceModule
import br.com.app.picpayclone.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AppApplication)
            modules(viewModelModule, serviceModule)
        }
    }

}