package com.iebayirli.appcent.ui.user_form

import android.os.Bundle
import com.iebayirli.appcent.R
import com.iebayirli.appcent.base.BaseFragment
import com.iebayirli.appcent.databinding.FragmentUserFormBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class UserFormFragment : BaseFragment<UserFormViewModel, FragmentUserFormBinding>() {


    override val layoutId = R.layout.fragment_user_form

    override val viewModel by viewModel<UserFormViewModel>()

    override fun initializeUI(savedInstanceState: Bundle?) {


    }


}