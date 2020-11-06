package com.iebayirli.appcent.ui.onboarding

import android.os.Bundle
import com.iebayirli.appcent.R
import com.iebayirli.appcent.base.BaseActivity
import com.iebayirli.appcent.databinding.ActivityOnboardingBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class OnboardingActivity : BaseActivity<OnboardingViewModel, ActivityOnboardingBinding>() {

    override val layoutId = R.layout.activity_onboarding

    override val viewModel by viewModel<OnboardingViewModel>()

    override fun initializeUI(savedInstanceState: Bundle?) {


    }

    override fun observe() {
    }
}