package com.iebayirli.appcent.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.iebayirli.appcent.base.BaseViewModel
import com.iebayirli.appcent.common.DialogState
import com.iebayirli.appcent.model.User
import com.iebayirli.appcent.repository.AchievementRepository
import com.iebayirli.appcent.repository.LastTransactionsRepository
import com.iebayirli.appcent.repository.UserRepository
import com.iebayirli.appcent.utils.Constants
import java.util.concurrent.TimeUnit


class LoginViewModel(
    private val firebaseAuth: FirebaseAuth,
    private val userRepository: UserRepository,
    private val achievementRepository: AchievementRepository,
    private val lastTransactionsRepository: LastTransactionsRepository
) : BaseViewModel() {

    private val _onCodeSend = MutableLiveData(false)
    fun getOnCodeSend(): LiveData<Boolean> = _onCodeSend

    private val _navigateToUserForm = MutableLiveData(false)
    fun getNavigateToUserForm(): LiveData<Boolean> = _navigateToUserForm

    private val _navigateToMain = MutableLiveData(false)
    fun getNavigateToMain(): LiveData<Boolean> = _navigateToMain

    var verificationId: String? = null

    val options by lazy {
        PhoneAuthOptions.newBuilder(firebaseAuth)
            .setTimeout(Constants.PHONE_VERIFY_TIMEOUT, TimeUnit.SECONDS)
            .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                    signInWithPhoneAuthCredential(credential)
                }

                override fun onVerificationFailed(exception: FirebaseException) {
                    _onCodeSend.postValue(false)
                }

                override fun onCodeSent(
                    verificationId: String,
                    provider: PhoneAuthProvider.ForceResendingToken
                ) {
                    _dialog.postValue(DialogState.HIDE)
                    super.onCodeSent(verificationId, provider)
                    this@LoginViewModel.verificationId = verificationId
                    _onCodeSend.postValue(true)
                }

            })
    }


    fun signInWithPhoneNumber(phoneNumber: String) {
        _dialog.postValue(DialogState.SHOW)
        user.phoneNumber = phoneNumber
        PhoneAuthProvider.verifyPhoneNumber(
            options
                .setPhoneNumber(phoneNumber)
                .build()
        )
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        _dialog.postValue(DialogState.SHOW)
        firebaseAuth.signInWithCredential(credential)
            .addOnSuccessListener { auth ->
                auth.user?.uid?.let {
                    userRepository.getUser(it).addOnSuccessListener { document ->
                        val user = document.toObject(User::class.java)
                        if (user != null) {
                            user.uid = it
                            syncUser(user)
                            _navigateToMain.postValue(true)
                            _dialog.postValue(DialogState.HIDE)
                        } else {
                            this@LoginViewModel.user.uid = it
                            userRepository.addUser(it, this@LoginViewModel.user)
                                .addOnSuccessListener {
                                    _dialog.postValue(DialogState.HIDE)
                                    _navigateToUserForm.postValue(true)
                                }.addOnFailureListener {
                                    _dialog.postValue(DialogState.HIDE)
                                }
                        }
                    }
                }
            }.addOnFailureListener {
                _dialog.postValue(DialogState.HIDE)
            }
    }

    fun verify(otpCode: String) {
        val credential = PhoneAuthProvider.getCredential(verificationId!!, otpCode)
        signInWithPhoneAuthCredential(credential)
    }

    fun getRandomString(length: Int): String {
        val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
        return (1..length)
            .map { allowedChars.random() }
            .joinToString("")
    }

    fun addTestUser() {
        _dialog.postValue(DialogState.SHOW)
        user.uid = getRandomString(10)
        user.phoneNumber = "+905555555555"
        user.name = "Test"
        user.email = "test@gmail.com"
        user.point = 0
        user.surname = ""
        user.phoneNumber = "+905555555555"
        userRepository.addUser(user.uid!!, this@LoginViewModel.user)
            .addOnSuccessListener {
                _dialog.postValue(DialogState.HIDE)
                _navigateToMain.postValue(true)
            }.addOnFailureListener {
                _dialog.postValue(DialogState.HIDE)
            }
    }

}
