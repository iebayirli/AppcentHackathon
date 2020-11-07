package com.iebayirli.appcent.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.QuerySnapshot
import com.iebayirli.appcent.base.BaseRepository
import com.iebayirli.appcent.model.LastTransactions


class LastTransactionsRepository : BaseRepository() {
    override val collectionName = "LastTransactions"

    fun getLastTransactions(userId: String): Task<QuerySnapshot> {
        return db.whereEqualTo("userId", userId).get()
    }

    fun addLastTransactions(lastTransactions: LastTransactions): Task<DocumentReference> {
        return db.add(lastTransactions)
    }
}