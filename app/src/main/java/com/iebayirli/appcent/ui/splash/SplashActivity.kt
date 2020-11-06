package com.iebayirli.appcent.ui.splash

import android.os.Bundle
import com.iebayirli.appcent.R
import com.iebayirli.appcent.base.BaseActivity
import com.iebayirli.appcent.databinding.ActivitySplashBinding
import com.iebayirli.appcent.ui.login.LoginActivity
import com.iebayirli.appcent.ui.main.MainActivity
import com.iebayirli.appcent.ui.onboarding.OnboardingActivity
import com.iebayirli.appcent.utils.createIntent
import com.iebayirli.appcent.utils.observeNotNull
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : BaseActivity<SplashViewModel, ActivitySplashBinding>() {


    override val layoutId = R.layout.activity_splash

    override val viewModel by viewModel<SplashViewModel>()

    override fun initializeUI(savedInstanceState: Bundle?) {
    }

    override fun observe() {
        viewModel.navigate.observeNotNull(this) {
            when (it) {
                SplashNavigateState.MAIN -> startActivity(MainActivity::class.java.createIntent(this))
                SplashNavigateState.ONBOARDING -> startActivity(
                    OnboardingActivity::class.java.createIntent(
                        this
                    )
                )
                else -> startActivity(LoginActivity::class.java.createIntent(this))
            }
        }
    }
}