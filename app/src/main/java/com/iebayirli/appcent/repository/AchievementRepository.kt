package com.iebayirli.appcent.repository

import com.iebayirli.appcent.base.BaseRepository
import com.iebayirli.appcent.model.Achievement


class AchievementRepository : BaseRepository() {

    override val collectionName = "Achievement"

    fun getAchievements(userPoint: Int, block: (List<Achievement>) -> Unit) {
        db.get().addOnSuccessListener { querySnapshot ->
            if (querySnapshot.documents.isNotEmpty()) {
                val documents = querySnapshot.documents
                val list = mutableListOf<Achievement>()
                documents.forEach {
                    val obj = it.toObject(Achievement::class.java)
                    obj?.let { achievement ->
                        achievement.isRewarded = (userPoint > achievement.limit ?: 0)
                        list.add(achievement)
                    }
                }
                block(list.sortedBy { it.limit })
            }
        }
    }
}