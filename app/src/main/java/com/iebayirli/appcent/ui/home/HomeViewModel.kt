package com.iebayirli.appcent.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.iebayirli.appcent.base.BaseViewModel
import com.iebayirli.appcent.common.DialogState
import com.iebayirli.appcent.model.Campaigns
import com.iebayirli.appcent.repository.CampaignsRepository
import com.iebayirli.appcent.repository.UserRepository

class HomeViewModel(
    private val campaignsRepository: CampaignsRepository,
    private val userRepository: UserRepository
) : BaseViewModel(), ItemClickListener {

    val itemClickListener: ItemClickListener = this

    private val _allCampaignData: MutableLiveData<List<Campaigns>> = MutableLiveData()
    val allCampaignData: LiveData<List<Campaigns>>
        get() = _allCampaignData

    private val _onItemClicked: MutableLiveData<Campaigns> = MutableLiveData()
    val onItemClicked: LiveData<Campaigns>
        get() = _onItemClicked

    init {
        setupCampaigns()
    }

    fun setupCampaigns() {
        _dialog.postValue(DialogState.SHOW)
        campaignsRepository.getCampaigns {
            _dialog.postValue(DialogState.HIDE)
            it.forEach {
                if (it.limit!! <= user.point && it.price!! <= user.point) {
                    it.isUsable = true
                }
            }
            _allCampaignData.value = it
        }
    }

    override fun onItemClicked(item: Campaigns) {
        _onItemClicked.postValue(item)
    }
}