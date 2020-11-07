package com.iebayirli.appcent.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.iebayirli.appcent.common.SharedPrefHelper
import com.iebayirli.appcent.model.User
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {

    single { FirebaseAuth.getInstance() }
    single { Firebase.firestore }
    single { SharedPrefHelper(androidContext()) }
    single { User() }
}