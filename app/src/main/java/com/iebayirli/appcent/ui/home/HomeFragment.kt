package com.iebayirli.appcent.ui.home

import android.os.Bundle
import com.iebayirli.appcent.R
import com.iebayirli.appcent.base.BaseFragment
import com.iebayirli.appcent.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {

    override val layoutId = R.layout.fragment_home

    override val viewModel by viewModel<HomeViewModel>()

    override fun initializeUI(savedInstanceState: Bundle?) {


    }

    override fun observe() {
    }
}