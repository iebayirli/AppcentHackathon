package com.iebayirli.appcent.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR

abstract class BaseActivity<VM : BaseViewModel, B : ViewDataBinding> : AppCompatActivity() {

    @get:LayoutRes
    protected abstract val layoutId: Int

    lateinit var binding: B

    abstract val viewModel: VM

    abstract fun initializeUI (savedInstanceState: Bundle?)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutId)
        binding.lifecycleOwner = this
        binding.setVariable(BR.viewModel, viewModel)
        initializeUI(savedInstanceState)
    }

}