package com.iebayirli.appcent.utils

import android.view.View
import androidx.annotation.IdRes
import androidx.databinding.BindingAdapter
import com.iebayirli.appcent.common.NavigatorListener

@BindingAdapter(value = ["navigate", "navigator"])
fun navigate(view: View, @IdRes destinationId: Int, navigator: NavigatorListener) {
    view.setOnClickListener {
        navigator.navigate(destinationId)
    }
}