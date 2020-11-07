package com.iebayirli.appcent.utils

import android.annotation.SuppressLint
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.iebayirli.appcent.R
import com.iebayirli.appcent.common.CommonRecyclerViewAdapter
import com.iebayirli.appcent.common.NavigatorListener
import com.iebayirli.appcent.common.OnItemSelectListener
import com.iebayirli.appcent.ui.main.BottomNavigateState

@BindingAdapter(value = ["navigate", "navigator"])
fun navigate(view: View, @IdRes destinationId: Int, navigator: NavigatorListener) {
    view.setOnClickListener {
        navigator.navigate(destinationId)
    }
}
@BindingAdapter(value = ["bottomNavigator"])
fun bottomNavigate(view: BottomNavigationView, navigator: OnItemSelectListener) {

    view.setOnNavigationItemSelectedListener(object :
        BottomNavigationView.OnNavigationItemSelectedListener {
        override fun onNavigationItemSelected(item: MenuItem): Boolean {
            when (item.itemId) {
                R.id.home -> navigator.onItemSelect(BottomNavigateState.HOME)
                R.id.rewards -> navigator.onItemSelect(BottomNavigateState.ACHIEVEMENTS)
                R.id.profile -> navigator.onItemSelect(BottomNavigateState.PROFILE)
                R.id.transactions -> navigator.onItemSelect(BottomNavigateState.TRANSACTIONS)
            }
            return true
        }
    })

}

@BindingAdapter(value = ["layoutRes", "data", "itemListener"], requireAll = false)
fun <T> setAdapter(
    recyclerView: RecyclerView,
    @LayoutRes resource: Int,
    data: LiveData<List<T>>,
    itemListener: Any?
) {
    if (recyclerView.adapter == null) {
        recyclerView.adapter =
            CommonRecyclerViewAdapter(resource, data.value ?: listOf(), itemListener)
    } else {
        if (recyclerView.adapter is CommonRecyclerViewAdapter<*>) {
            val items = data.value ?: listOf()
            (recyclerView.adapter as CommonRecyclerViewAdapter<T>).updateData(items)
        }
    }
}


@BindingAdapter(value = ["setImage"])
fun setImage(imageView: ImageView, imageUrl: String?) {
    if (imageUrl.isNullOrEmpty().not()) {
        Glide.with(imageView.context).load(imageUrl).into(imageView)
    }
}

@SuppressLint("SetTextI18n")
@BindingAdapter(value = ["setText"])
fun setTextForPrice(textView: TextView, text: String?) {
    if (text.isNullOrEmpty().not()) {
        textView.text = "$text Puan"
    }
}
