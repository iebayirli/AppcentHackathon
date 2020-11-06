package com.iebayirli.appcent.ui.profile

import android.os.Bundle
import com.iebayirli.appcent.R
import com.iebayirli.appcent.base.BaseFragment
import com.iebayirli.appcent.databinding.FragmentProfileBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class ProfileFragment : BaseFragment<ProfileViewModel, FragmentProfileBinding>() {

    override val layoutId = R.layout.fragment_profile

    override val viewModel by viewModel<ProfileViewModel>()

    override fun initializeUI(savedInstanceState: Bundle?) {


    }

    override fun observe() {
    }

}