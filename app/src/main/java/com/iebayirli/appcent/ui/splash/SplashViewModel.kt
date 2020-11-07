package com.iebayirli.appcent.ui.splash

import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.iebayirli.appcent.base.BaseViewModel
import com.iebayirli.appcent.common.LiveEvent
import com.iebayirli.appcent.common.SharedPrefHelper
import com.iebayirli.appcent.common.SharedPrefKey
import com.iebayirli.appcent.model.User
import com.iebayirli.appcent.repository.UserRepository
import com.iebayirli.appcent.utils.Constants
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel(
    var mFirebaseAuth: FirebaseAuth,
    private val sharedPrefHelper: SharedPrefHelper,
    private val userRepository: UserRepository
) : BaseViewModel() {


    val welcome = "Splash Screen"
    val navigate = LiveEvent<SplashNavigateState>()

    init {
        viewModelScope.launch {
            delay(Constants.SPLASH_TIMEOUT)

            sharedPrefHelper.get {
                if (getBoolean(SharedPrefKey.IS_ONBOARDING_SHOWED.toString(), false).not())
                    navigate.postValue(SplashNavigateState.ONBOARDING)
                sharedPrefHelper.add {
                    putBoolean(SharedPrefKey.IS_ONBOARDING_SHOWED.toString(), true)
                }
            }

            getUser()?.let {
                userRepository.getUser(it.uid).addOnSuccessListener { document ->
                    val dbUser = document.toObject(User::class.java)
                    if (dbUser != null) {
                        dbUser.uid = it.uid
                        syncUser(dbUser)
                        navigate.postValue(SplashNavigateState.MAIN)
                    } else {
                        navigate.postValue(SplashNavigateState.LOGIN)
                    }
                }
            } ?: kotlin.run {
                navigate.postValue(SplashNavigateState.LOGIN)
            }


        }
    }

    private fun getUser(): FirebaseUser? = mFirebaseAuth.currentUser


}