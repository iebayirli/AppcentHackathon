package com.iebayirli.appcent.ui.user_form

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.iebayirli.appcent.base.BaseViewModel
import com.iebayirli.appcent.common.DialogState
import com.iebayirli.appcent.model.User
import com.iebayirli.appcent.repository.UserRepository

class UserFormViewModel(private val userRepository: UserRepository) : BaseViewModel() {

    var nameChangedData = ObservableField<String>(user.name ?: "")
    var surnameChangedData = ObservableField<String>(user.surname ?: "")
    var emailChangedData = ObservableField<String>(user.email ?: "")

    private val _navigateToMain = MutableLiveData(false)
    fun getNavigateToMain(): LiveData<Boolean> = _navigateToMain

    fun save() {

        val name = nameChangedData.get() ?: ""
        val surname = surnameChangedData.get() ?: ""
        val email = emailChangedData.get() ?: ""
        val point = 0

        val newUser = User().apply {
            uid = user.uid
            phoneNumber = user.phoneNumber
            this.name = name
            this.surname = surname
            this.email = email
            this.point = point

        }
        _dialog.postValue(DialogState.SHOW)
        userRepository.updateUser(
            user.uid!!, hashMapOf(
                "name" to name,
                "surname" to surname,
                "email" to email,
                "point" to point
            )
        ).addOnSuccessListener {
            syncUser(newUser)
            _dialog.postValue(DialogState.HIDE)
            _navigateToMain.postValue(true)
        }.addOnFailureListener {
            _dialog.postValue(DialogState.HIDE)
        }
    }
}