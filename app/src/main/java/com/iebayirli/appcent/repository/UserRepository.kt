package com.iebayirli.appcent.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.iebayirli.appcent.base.BaseRepository
import com.iebayirli.appcent.model.User

class UserRepository : BaseRepository() {

    override val collectionName = "User"

    fun addUser(userId: String, user: User): Task<Void> {
        return db.document(userId).set(user)
    }

    fun updateUser(userId: String, hashMap: HashMap<String, Any>): Task<Void> {
        return db.document(userId).update(hashMap)
    }

    fun getUser(userId: String): Task<DocumentSnapshot> {
        return db.document(userId).get()
    }


    fun getLeaderboard(block: (List<User>) -> Unit) {
        db.get().addOnSuccessListener {
            if (it.documents.isNotEmpty()) {
                val users = it.toObjects(User::class.java)
                users.sortByDescending { it.point }
                block(users)
            }
        }
    }

}