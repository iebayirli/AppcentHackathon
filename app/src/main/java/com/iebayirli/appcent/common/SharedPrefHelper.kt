package com.iebayirli.appcent.common

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.iebayirli.appcent.utils.Constants.PREF_NAME

class SharedPrefHelper(context: Context) {


    private val sharedPreferences: SharedPreferences by lazy {
        context.getSharedPreferences(PREF_NAME, MODE_PRIVATE)
    }

    fun add(block: SharedPreferences.Editor.() -> Unit) {
        sharedPreferences.edit().apply {
            block()
            apply()
        }
    }

    fun get(block: SharedPreferences.() -> Unit) {
        sharedPreferences.block()
    }
}

enum class SharedPrefKey {
    IS_ONBOARDING_SHOWED
}
