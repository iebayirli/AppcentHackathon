package com.iebayirli.appcent.repository

import com.iebayirli.appcent.base.BaseRepository
import com.iebayirli.appcent.model.Campaigns


class CampaignsRepository : BaseRepository() {

    override val collectionName = "Campaigns"

    fun getCampaigns(block: (List<Campaigns>) -> Unit) {
        db.get().addOnSuccessListener { querySnapshot ->
            if (querySnapshot.documents.isNotEmpty()) {
                val documents = querySnapshot.documents
                val list = mutableListOf<Campaigns>()
                documents.forEach {
                    val obj = it.toObject(Campaigns::class.java)
                    obj?.let { campaign ->
                        list.add(campaign)
                    }
                }
                block(list)
            }
        }
    }

}