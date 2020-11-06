package com.iebayirli.appcent.di

import com.iebayirli.appcent.base.BaseRepository
import com.iebayirli.appcent.base.FirebaseWrapper
import org.koin.dsl.module

val repositoryModule = module {
    single { FirebaseWrapper() }
}