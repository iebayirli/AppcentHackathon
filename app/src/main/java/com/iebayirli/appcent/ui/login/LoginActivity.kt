package com.iebayirli.appcent.ui.login

import android.os.Bundle
import com.iebayirli.appcent.R
import com.iebayirli.appcent.base.BaseActivity
import com.iebayirli.appcent.databinding.ActivityLoginBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : BaseActivity<ParentLoginViewModel, ActivityLoginBinding>() {


    override val layoutId = R.layout.activity_login

    override val viewModel by viewModel<ParentLoginViewModel>()

    override fun initializeUI(savedInstanceState: Bundle?) {

    }

    override fun observe() {
    }
}