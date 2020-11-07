package com.iebayirli.appcent.ui.transactions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.iebayirli.appcent.base.BaseViewModel
import com.iebayirli.appcent.common.DialogState
import com.iebayirli.appcent.model.LastTransactions
import com.iebayirli.appcent.repository.LastTransactionsRepository

class TransactionsViewModel(private val transactionsRepository: LastTransactionsRepository) :
    BaseViewModel() {

    private val _lastTransactions: MutableLiveData<List<LastTransactions>> = MutableLiveData()
    val lastTransactions: LiveData<List<LastTransactions>>
        get() = _lastTransactions


    init {
        setupTransactions()
    }

    fun setupTransactions() {
        _dialog.postValue(DialogState.SHOW)
        transactionsRepository.getLastTransactions(user.uid!!) {
            _lastTransactions.value = it
            _dialog.postValue(DialogState.HIDE)
        }
    }

}
