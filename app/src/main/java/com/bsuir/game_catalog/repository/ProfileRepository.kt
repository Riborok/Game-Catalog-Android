package com.bsuir.game_catalog.repository

import com.bsuir.game_catalog.model.UserProfile
import com.bsuir.game_catalog.utils.FireCollection
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class ProfileRepository {

    private val firestore = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()
    private val uid
        get() = auth.currentUser?.uid

    fun getUserProfile(onResult: (Result<UserProfile>) -> Unit) {
        validateUid(onResult) {
            firestore.collection(FireCollection.USERS)
                .document(uid!!)
                .get()
                .addOnSuccessListener { document ->
                    val profile = document.toObject(UserProfile::class.java) ?: UserProfile()
                    onResult(Result.success(profile))
                }
                .addOnFailureListener { onFailureListener(it, onResult) }
        }
    }

    fun saveUserProfile(profile: UserProfile, onResult: (Result<UserProfile>) -> Unit) {
        validateUid(onResult) {
            firestore.collection(FireCollection.USERS)
                .document(uid!!)
                .set(profile)
                .addOnSuccessListener { onResult(Result.success(profile)) }
                .addOnFailureListener { onFailureListener(it, onResult) }
        }
    }

    private fun validateUid(
        onResult: (Result<UserProfile>) -> Unit,
        action: () -> Unit
    ) {
        if (uid == null) {
            onResult(Result.failure(Exception()))
        } else {
            action()
        }
    }

    private fun onFailureListener(exception: Exception, onResult: (Result<UserProfile>) -> Unit) {
        onResult(Result.failure(exception))
    }
}
