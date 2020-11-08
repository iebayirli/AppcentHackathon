package com.iebayirli.appcent.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.iebayirli.appcent.common.DialogState
import com.iebayirli.appcent.model.User
import org.koin.core.KoinComponent
import org.koin.core.inject

abstract class BaseViewModel() : ViewModel(), KoinComponent {

    val user by inject<User>()

    private val _destinationId = MutableLiveData<Int>()
    fun getDestinationId(): LiveData<Int> = _destinationId

    protected val _dialog = MutableLiveData<DialogState>()
    fun getDialog(): LiveData<DialogState> = _dialog


    fun navigate(destinationId: Int) {
        _destinationId.postValue(destinationId)
    }

    protected fun syncUser(user: User) {
        this.user.phoneNumber = user.phoneNumber
        this.user.uid = user.uid
        this.user.email = user.email
        this.user.surname = user.surname
        this.user.name = user.name
        this.user.point = user.point
        this.user.highestPoint = user.highestPoint
    }
}