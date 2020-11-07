package com.iebayirli.appcent.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.iebayirli.appcent.base.BaseViewModel
import com.iebayirli.appcent.common.DialogState
import com.iebayirli.appcent.model.Campaigns
import com.iebayirli.appcent.repository.CampaignsRepository

class HomeViewModel(private val campaignsRepository: CampaignsRepository) : BaseViewModel() {

    private val _allCampaignData: MutableLiveData<List<Campaigns>> = MutableLiveData()
    val allCampaignData: LiveData<List<Campaigns>>
        get() = _allCampaignData

    init {
        _dialog.postValue(DialogState.SHOW)
        campaignsRepository.getCampaigns {
            _dialog.postValue(DialogState.HIDE)
            _allCampaignData.value = it
        }
    }
}