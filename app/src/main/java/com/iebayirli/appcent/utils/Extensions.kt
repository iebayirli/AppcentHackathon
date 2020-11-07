package com.iebayirli.appcent.utils

import android.content.Context
import android.content.Intent
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.iebayirli.appcent.base.BaseActivity

fun <T> LiveData<T>.observeNotNull(owner: LifecycleOwner, block: (T) -> Unit) {
    this.observe(owner, Observer {
        it?.let(block)
    })
}

fun <T : BaseActivity<*, *>> Class<T>.createIntent(context: Context) = Intent(context, this)

fun <T> MutableLiveData(data: T) = androidx.lifecycle.MutableLiveData<T>().apply {
    value = data
}