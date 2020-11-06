package com.iebayirli.appcent.ui.splash

import androidx.lifecycle.viewModelScope
import com.iebayirli.appcent.base.BaseViewModel
import com.iebayirli.appcent.common.LiveEvent
import com.iebayirli.appcent.utils.Constants
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel : BaseViewModel() {

    val welcome = "Splash Screen"
    val navigate = LiveEvent<SplashNavigateState>()

    init {
        viewModelScope.launch {
            delay(Constants.SPLASH_TIMEOUT)
            navigate.postValue(SplashNavigateState.LOGIN)
        }
    }
}