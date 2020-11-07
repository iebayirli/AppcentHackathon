package com.iebayirli.appcent.ui.main

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.iebayirli.appcent.base.BaseViewModel
import com.iebayirli.appcent.common.DialogState
import com.iebayirli.appcent.common.LiveEvent
import com.iebayirli.appcent.common.OnItemSelectListener
import com.iebayirli.appcent.model.Campaigns
import com.iebayirli.appcent.repository.CampaignsRepository
import com.iebayirli.appcent.repository.UserRepository


class MainViewModel(
    private val campaignsRepository: CampaignsRepository,
    private val userRepository: UserRepository
) : BaseViewModel(),
    OnItemSelectListener {

    val navigate = LiveEvent<BottomNavigateState>()
    var currentState: BottomNavigateState = BottomNavigateState.HOME

    var userName = ObservableField("Hoşgeldin ${user.name}")
    private val _pointInformation = MutableLiveData(user.point.toString() + " Puan")
    val pointInformation: LiveData<String>
        get() = _pointInformation

    val openQRDialog = LiveEvent<Any>()
    val closeQRDialog = LiveEvent<Any>()


    private val _allCampaignData: MutableLiveData<List<Campaigns>> = MutableLiveData()
    val allCampaignData: LiveData<List<Campaigns>>
        get() = _allCampaignData

    init {
        campaignsRepository.getCampaigns {
            _allCampaignData.value = it
        }
    }

    val itemSelectListener: OnItemSelectListener? = this
    override fun onItemSelect(state: BottomNavigateState) {
        if (currentState != state) {
            currentState = state
            navigate.postValue(state)
        }
    }

    fun openDialogClicked() {
        openQRDialog.postValue("OpenDialog")
    }

    fun qrCodeRead() {
        _dialog.postValue(DialogState.SHOW)
        val point = user.point + 15
        userRepository.updateUser(
            user.uid!!, hashMapOf(
                "point" to point
            )
        ).addOnSuccessListener {
            user.point = point
            _pointInformation.postValue("$point Puan")
            closeQRDialog.postValue("CloseDialog")
            _dialog.postValue(DialogState.HIDE)
        }.addOnFailureListener {
            _dialog.postValue(DialogState.HIDE)
        }
    }

}