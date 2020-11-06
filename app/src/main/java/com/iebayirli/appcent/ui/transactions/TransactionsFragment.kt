package com.iebayirli.appcent.ui.transactions

import android.os.Bundle
import com.iebayirli.appcent.R
import com.iebayirli.appcent.base.BaseFragment
import com.iebayirli.appcent.databinding.FragmentTransactionsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class TransactionsFragment : BaseFragment<TransactionsViewModel, FragmentTransactionsBinding>() {

    override val layoutId = R.layout.fragment_transactions

    override val viewModel by viewModel<TransactionsViewModel>()

    override fun initializeUI(savedInstanceState: Bundle?) {


    }


}