package com.iebayirli.appcent.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.iebayirli.appcent.base.BaseRepository
import com.iebayirli.appcent.model.LastTransactions


class LastTransactionsRepository : BaseRepository() {
    override val collectionName = "LastTransactions"

    fun getLastTransactions(userId: String, block: (List<LastTransactions>) -> Unit) {
        db.whereEqualTo("userId", userId).get().apply {
            this.addOnSuccessListener { querySnapshot ->
                if (querySnapshot.documents.isNotEmpty()) {
                    val documents = querySnapshot.documents
                    val list = mutableListOf<LastTransactions>()
                    documents.forEach {
                        val obj = it.toObject(LastTransactions::class.java)
                        obj?.let { campaign ->
                            list.add(campaign)
                        }
                    }
                    block(list)
                }
            }
        }
    }

    fun addLastTransactions(lastTransactions: LastTransactions): Task<DocumentReference> {
        return db.add(lastTransactions)
    }
}