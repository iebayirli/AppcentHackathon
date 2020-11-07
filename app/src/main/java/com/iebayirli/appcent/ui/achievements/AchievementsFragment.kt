package com.iebayirli.appcent.ui.achievements

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.iebayirli.appcent.R
import com.iebayirli.appcent.base.BaseFragment
import com.iebayirli.appcent.databinding.FragmentAchievementsBinding
import com.iebayirli.appcent.utils.observeNotNull
import org.koin.androidx.viewmodel.ext.android.viewModel


class AchievementsFragment : BaseFragment<AchievementsViewModel, FragmentAchievementsBinding>() {


    override val layoutId = R.layout.fragment_achievements

    override val viewModel by viewModel<AchievementsViewModel>()

    override fun initializeUI(savedInstanceState: Bundle?) {
        binding.recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
    }

    override fun observe() {
        viewModel.getAchievements().observeNotNull(this) {
            binding.recyclerView.adapter = AchievementsAdapter(it)
        }
    }
}