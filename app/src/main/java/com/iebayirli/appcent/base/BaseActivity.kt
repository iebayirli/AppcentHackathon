package com.iebayirli.appcent.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import com.iebayirli.appcent.common.DialogState
import com.iebayirli.appcent.utils.observeNotNull
import com.kaopiz.kprogresshud.KProgressHUD

abstract class BaseActivity<VM : BaseViewModel, B : ViewDataBinding> : AppCompatActivity() {

    @get:LayoutRes
    protected abstract val layoutId: Int

    lateinit var binding: B

    abstract val viewModel: VM

    abstract fun initializeUI(savedInstanceState: Bundle?)

    abstract fun observe()

    val dialog by lazy {
        KProgressHUD.create(this)
            .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
            .setCancellable(true)
            .setAnimationSpeed(2)
            .setDimAmount(0.5f)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutId)
        binding.lifecycleOwner = this
        binding.setVariable(BR.viewModel, viewModel)
        initializeUI(savedInstanceState)
        observe()
        viewModel.getDialog().observeNotNull(this) {
            if (it == DialogState.HIDE)
                dialog.dismiss()
            else
                dialog.show()
        }
    }

}