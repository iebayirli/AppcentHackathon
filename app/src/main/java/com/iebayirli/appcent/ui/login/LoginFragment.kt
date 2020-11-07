package com.iebayirli.appcent.ui.login


import android.os.Bundle
import androidx.navigation.fragment.findNavController
import com.iebayirli.appcent.R
import com.iebayirli.appcent.base.BaseFragment
import com.iebayirli.appcent.databinding.FragmentLoginBinding
import com.iebayirli.appcent.ui.main.MainActivity
import com.iebayirli.appcent.utils.createIntent
import com.iebayirli.appcent.utils.observeNotNull
import org.koin.androidx.viewmodel.ext.android.viewModel


class LoginFragment : BaseFragment<LoginViewModel, FragmentLoginBinding>() {


    override val layoutId = R.layout.fragment_login

    override val viewModel by viewModel<LoginViewModel>()

    override fun initializeUI(savedInstanceState: Bundle?) {
        viewModel.options.setActivity(requireActivity())
    }

    override fun observe() {
        viewModel.getOnCodeSend().observeNotNull(this) {
            if (it) {
                binding.editTextPhone.setText("")
            }
        }

        viewModel.getNavigateToUserForm().observeNotNull(this) {
            if (it) {
                findNavController().navigate(R.id.action_loginFragment_to_userFormFragment)
            }
        }

        viewModel.getNavigateToMain().observeNotNull(this) {
            if (it) {
                startActivity(MainActivity::class.java.createIntent(requireContext()))
            }
        }
    }
}