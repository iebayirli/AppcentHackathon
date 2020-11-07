package com.iebayirli.appcent.ui.achievements

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.iebayirli.appcent.base.BaseViewModel
import com.iebayirli.appcent.common.DialogState
import com.iebayirli.appcent.model.Achievement
import com.iebayirli.appcent.repository.AchievementRepository

class AchievementsViewModel(private val achievementRepository: AchievementRepository) :
    BaseViewModel() {

    private val _achievements = MutableLiveData<List<Achievement>>()
    fun getAchievements(): LiveData<List<Achievement>> = _achievements


    init {
        setupAchievements()
    }

    fun setupAchievements() {
        _dialog.postValue(DialogState.SHOW)
        achievementRepository.getAchievements(user.point) {
            _achievements.postValue(it)
            _dialog.postValue(DialogState.HIDE)
        }
    }
}