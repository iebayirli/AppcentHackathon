package com.iebayirli.appcent.ui.login


import android.os.Bundle
import com.iebayirli.appcent.R
import com.iebayirli.appcent.base.BaseFragment
import com.iebayirli.appcent.databinding.FragmentLoginBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class LoginFragment : BaseFragment<LoginViewModel, FragmentLoginBinding>() {


    override val layoutId = R.layout.fragment_login

    override val viewModel by viewModel<LoginViewModel>()

    override fun initializeUI(savedInstanceState: Bundle?) {

    }

    override fun observe() {

    }
}