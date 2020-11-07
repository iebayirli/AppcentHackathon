package com.iebayirli.appcent.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.iebayirli.appcent.base.BaseViewModel
import com.iebayirli.appcent.common.DialogState
import com.iebayirli.appcent.model.User
import com.iebayirli.appcent.repository.UserRepository

class ProfileViewModel(private val userRepository: UserRepository) : BaseViewModel() {

    private val _navigateToUserForm = MutableLiveData(false)
    fun getNavigateToUserForm(): LiveData<Boolean> = _navigateToUserForm

    private val _leaderboard = MutableLiveData<List<User>>()
    fun getLeaderboard(): LiveData<List<User>> = _leaderboard

    fun editProfile() {
        _navigateToUserForm.postValue(true)
    }

    init {
        setupLeaderboard()
    }

    fun setupLeaderboard() {
        _dialog.postValue(DialogState.SHOW)
        userRepository.getLeaderboard {
            _leaderboard.postValue(it)
            _dialog.postValue(DialogState.HIDE)
        }
    }
}