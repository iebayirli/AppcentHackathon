package com.iebayirli.appcent.ui.transactions

import android.os.Bundle
import com.iebayirli.appcent.R
import com.iebayirli.appcent.base.BaseFragment
import com.iebayirli.appcent.databinding.FragmentTransactionsBinding
import com.iebayirli.appcent.ui.main.MainViewModel
import com.iebayirli.appcent.utils.observeNotNull
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class TransactionsFragment : BaseFragment<TransactionsViewModel, FragmentTransactionsBinding>() {

    override val layoutId = R.layout.fragment_transactions

    override val viewModel by viewModel<TransactionsViewModel>()
    private val mainViewModel by sharedViewModel<MainViewModel>()

    override fun initializeUI(savedInstanceState: Bundle?) {


    }

    override fun observe() {
        mainViewModel.pointInformation.observeNotNull(this) {
            viewModel.setupTransactions()
        }
    }

}