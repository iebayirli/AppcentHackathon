package com.iebayirli.appcent.ui.user_form

import android.os.Bundle
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.iebayirli.appcent.R
import com.iebayirli.appcent.base.BaseFragment
import com.iebayirli.appcent.databinding.FragmentUserFormBinding
import com.iebayirli.appcent.ui.main.MainActivity
import com.iebayirli.appcent.ui.main.MainViewModel
import com.iebayirli.appcent.utils.createIntent
import com.iebayirli.appcent.utils.observeNotNull
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class UserFormFragment : BaseFragment<UserFormViewModel, FragmentUserFormBinding>() {


    override val layoutId = R.layout.fragment_user_form

    override val viewModel by viewModel<UserFormViewModel>()
    val mainViewModel by sharedViewModel<MainViewModel>()


    override fun initializeUI(savedInstanceState: Bundle?) {


    }

    override fun observe() {
        viewModel.getNavigateToMain().observeNotNull(this) {
            if (it) {
                if (arguments?.getBoolean("EDIT_MODE", false) == true) {
                    Toast.makeText(requireContext(), "Kaydedildi", Toast.LENGTH_SHORT).show()
                    mainViewModel.updateUsername()
                    findNavController().navigate(R.id.action_gotoProfile)
                } else {
                    startActivity(MainActivity::class.java.createIntent(requireContext()))
                }
            }
        }
    }

}