package com.iebayirli.appcent.ui.achievements

import android.os.Bundle
import com.iebayirli.appcent.R
import com.iebayirli.appcent.base.BaseFragment
import com.iebayirli.appcent.databinding.FragmentAchievementsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class AchievementsFragment : BaseFragment<AchievementsViewModel, FragmentAchievementsBinding>() {


    override val layoutId = R.layout.fragment_achievements

    override val viewModel by viewModel<AchievementsViewModel>()

    override fun initializeUI(savedInstanceState: Bundle?) {


    }

}