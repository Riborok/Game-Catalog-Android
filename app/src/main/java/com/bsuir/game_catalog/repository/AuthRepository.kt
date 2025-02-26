package com.bsuir.game_catalog.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class AuthRepository {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    val currentUser = auth.currentUser

    fun signIn(email: String, password: String, onResult: (Result<FirebaseUser?>) -> Unit) {
        validateCredentials(email, password, onResult) {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        onResult(Result.success(auth.currentUser))
                    } else {
                        onResult(Result.failure(task.exception ?: Exception()))
                    }
                }
        }
    }

    fun signUp(email: String, password: String, onResult: (Result<FirebaseUser?>) -> Unit) {
        validateCredentials(email, password, onResult) {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        onResult(Result.success(auth.currentUser))
                    } else {
                        onResult(Result.failure(task.exception ?: Exception()))
                    }
                }
        }
    }

    private fun validateCredentials(
        email: String, password: String,
        onResult: (Result<FirebaseUser?>) -> Unit,
        action: () -> Unit
    ) {
        if (email.trim().isEmpty() || password.trim().isEmpty()) {
            onResult(Result.failure(Exception()))
        } else {
            action()
        }
    }

    fun signOut() {
        auth.signOut()
    }
}