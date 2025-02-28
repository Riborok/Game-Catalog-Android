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
            getUserProfile(uid!!, onResult)
        }
    }

    fun getUserProfile(docId: String, onResult: (Result<UserProfile>) -> Unit) {
        firestore.collection(FireCollection.USERS)
            .document(docId)
            .get()
            .addOnSuccessListener { document ->
                val profile = document.toObject(UserProfile::class.java) ?: UserProfile()
                onResult(Result.success(profile))
            }
            .addOnFailureListener { onFailureListener(it, onResult) }
    }

    fun saveUserProfile(profile: UserProfile, onResult: (Result<UserProfile>) -> Unit) {
        validateUid(onResult) {
            saveUserProfile(uid!!, profile, onResult)
        }
    }

    fun saveUserProfile(docId: String, profile: UserProfile, onResult: (Result<UserProfile>) -> Unit) {
        firestore.collection(FireCollection.USERS)
            .document(docId)
            .set(profile)
            .addOnSuccessListener { onResult(Result.success(profile)) }
            .addOnFailureListener { onFailureListener(it, onResult) }
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
