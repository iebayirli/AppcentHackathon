package com.iebayirli.appcent.ui.main

import android.os.Bundle
import com.iebayirli.appcent.R
import com.iebayirli.appcent.base.BaseActivity
import com.iebayirli.appcent.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {


    override val layoutId = R.layout.activity_main

    override val viewModel by viewModel<MainViewModel>()

    override fun initializeUI(savedInstanceState: Bundle?) {
    }

    override fun observe() {
    }


}