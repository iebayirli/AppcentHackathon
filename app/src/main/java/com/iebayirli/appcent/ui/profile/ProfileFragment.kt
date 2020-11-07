package com.iebayirli.appcent.ui.profile

import android.os.Bundle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.iebayirli.appcent.R
import com.iebayirli.appcent.base.BaseFragment
import com.iebayirli.appcent.databinding.FragmentProfileBinding
import com.iebayirli.appcent.ui.main.MainViewModel
import com.iebayirli.appcent.ui.user_form.UserFormFragmentDirections
import com.iebayirli.appcent.utils.observeNotNull
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class ProfileFragment : BaseFragment<ProfileViewModel, FragmentProfileBinding>() {

    override val layoutId = R.layout.fragment_profile

    override val viewModel by viewModel<ProfileViewModel>()
    val mainViewModel by sharedViewModel<MainViewModel>()

    override fun initializeUI(savedInstanceState: Bundle?) {
        binding.recyclerView2.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

    }

    override fun observe() {
        viewModel.getNavigateToUserForm().observeNotNull(this) {
            if (it) {
                findNavController().navigate(
                    UserFormFragmentDirections.actionGotouserFormFragment2(
                        true
                    )
                )
            }
        }

        viewModel.getLeaderboard().observeNotNull(this) {
            binding.recyclerView2.adapter = LeaderboardAdapter(it, viewModel.user.uid ?: "")
        }

        mainViewModel.pointInformation.observeNotNull(this) {
            viewModel.setupLeaderboard()
        }
    }

}