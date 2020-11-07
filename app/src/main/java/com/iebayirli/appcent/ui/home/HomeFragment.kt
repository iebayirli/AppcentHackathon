package com.iebayirli.appcent.ui.home

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.iebayirli.appcent.R
import com.iebayirli.appcent.base.BaseFragment
import com.iebayirli.appcent.databinding.FragmentHomeBinding
import com.iebayirli.appcent.ui.main.MainViewModel
import com.iebayirli.appcent.ui.transactions.TransactionState
import com.iebayirli.appcent.utils.observeNotNull
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {

    override val layoutId = R.layout.fragment_home

    override val viewModel by viewModel<HomeViewModel>()
    val mainViewModel by sharedViewModel<MainViewModel>()

    override fun initializeUI(savedInstanceState: Bundle?) {


    }

    override fun observe() {
        mainViewModel.pointInformation.observeNotNull(this) {
            viewModel.setupCampaigns()
        }

        viewModel.onItemClicked.observeNotNull(this) {
            AlertDialog.Builder(requireContext())
                .setTitle("Puan Kulanma")
                .setMessage("${it.price} puan karşılığı ürünü satın almak istediğinize emin misiniz?") // Specifying a listener allows you to take an action before dismissing the dialog.
                .setPositiveButton(
                    android.R.string.yes
                ) { dialog, which ->
                    mainViewModel.updatePoint(
                        (it.price ?: 0) * -1,
                        "${TransactionState.KAMPANYA} - ${it.header}"
                    )
                }
                .setNegativeButton(android.R.string.no, null)
                .show()
        }
    }
}