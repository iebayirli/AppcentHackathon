package com.iebayirli.appcent.ui.splash

import android.os.Bundle
import com.iebayirli.appcent.R
import com.iebayirli.appcent.base.BaseActivity
import com.iebayirli.appcent.databinding.ActivitySplashBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : BaseActivity<SplashViewModel, ActivitySplashBinding>() {


    override val layoutId = R.layout.activity_splash

    override val viewModel by viewModel<SplashViewModel>()

    override fun initializeUI(savedInstanceState: Bundle?) {
    }

}