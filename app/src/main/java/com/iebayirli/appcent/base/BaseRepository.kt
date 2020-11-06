package com.iebayirli.appcent.base

import org.koin.core.KoinComponent
import org.koin.core.inject

abstract class BaseRepository(): KoinComponent {
    private val firebase by inject<FirebaseWrapper>()
}