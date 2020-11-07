package com.iebayirli.appcent.ui.main

import android.os.Bundle
import androidx.navigation.findNavController
import com.iebayirli.appcent.R
import com.iebayirli.appcent.base.BaseActivity
import com.iebayirli.appcent.databinding.ActivityMainBinding
import com.iebayirli.appcent.utils.observeNotNull
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {


    override val layoutId = R.layout.activity_main

    override val viewModel by viewModel<MainViewModel>()

    private val navController by lazy {
        findNavController(R.id.fragment_container_main)
    }

    override fun initializeUI(savedInstanceState: Bundle?) {
    }

    override fun observe() {
        viewModel.navigate.observeNotNull(this) {
            when (it) {
                BottomNavigateState.HOME -> navController.navigate(R.id.action_gotoHome)
                BottomNavigateState.ACHIEVEMENTS -> navController.navigate(R.id.action_gotoAchievements)
                BottomNavigateState.PROFILE -> navController.navigate(R.id.action_gotoProfile)
            }
        }

        viewModel.openQRDialog.observeNotNull(this) {
            QRDialog().show(supportFragmentManager, "BottomSheet")
        }


    }


}