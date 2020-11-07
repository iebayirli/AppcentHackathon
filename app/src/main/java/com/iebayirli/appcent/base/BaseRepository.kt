package com.iebayirli.appcent.base

import com.google.firebase.firestore.FirebaseFirestore
import org.koin.core.KoinComponent
import org.koin.core.inject

abstract class BaseRepository(): KoinComponent {
    private val firestore by inject<FirebaseFirestore>()

    protected val db by lazy { firestore.collection(collectionName) }

    protected abstract val collectionName: String
}