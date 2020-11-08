package com.iebayirli.appcent.ui.main

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.iebayirli.appcent.base.BaseViewModel
import com.iebayirli.appcent.common.DialogState
import com.iebayirli.appcent.common.LiveEvent
import com.iebayirli.appcent.common.OnItemSelectListener
import com.iebayirli.appcent.model.Campaigns
import com.iebayirli.appcent.model.LastTransactions
import com.iebayirli.appcent.repository.CampaignsRepository
import com.iebayirli.appcent.repository.LastTransactionsRepository
import com.iebayirli.appcent.repository.UserRepository
import com.iebayirli.appcent.ui.transactions.TransactionState


class MainViewModel(
    private val campaignsRepository: CampaignsRepository,
    private val userRepository: UserRepository,
    private val transactionsRepository: LastTransactionsRepository
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
        updatePoint(15, "${TransactionState.KAZANC} - QR")

    }

    fun updatePoint(value: Int, name: String) {
        _dialog.postValue(DialogState.SHOW)
        val point = user.point + value
        val map: HashMap<String, Any> = hashMapOf(
            "point" to point
        )
        if (point > user.highestPoint) {
            map["highestPoint"] = point
        }
        userRepository.updateUser(
            user.uid!!, map
        ).addOnSuccessListener {
            user.point = point
            if (point > user.highestPoint) {
                user.highestPoint = point
            }
            _pointInformation.postValue("$point Puan")
            closeQRDialog.postValue("CloseDialog")
            _dialog.postValue(DialogState.HIDE)
        }.addOnFailureListener {
            _dialog.postValue(DialogState.HIDE)
        }
        transactionsRepository.addLastTransactions(LastTransactions(name, value, user.uid))
    }


}