package com.iebayirli.appcent.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel(): ViewModel() {
    private val _destinationId = MutableLiveData<Int>()
    fun getDestinationId(): LiveData<Int> = _destinationId

    fun navigate(destinationId: Int) {
        _destinationId.postValue(destinationId)
    }
}