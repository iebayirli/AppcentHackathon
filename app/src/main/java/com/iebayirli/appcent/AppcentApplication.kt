package com.iebayirli.appcent

import android.app.Application
import com.iebayirli.appcent.di.appModule
import com.iebayirli.appcent.di.repositoryModule
import com.iebayirli.appcent.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppcentApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@AppcentApplication)
            modules(
                appModule,
                viewModelModule,
                repositoryModule
            )
        }

    }
}