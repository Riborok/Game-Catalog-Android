package com.bsuir.game_catalog.repository

import com.bsuir.game_catalog.model.Favorite
import com.bsuir.game_catalog.utils.FireCollection
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class FavoriteRepository {

    private val firestore = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()
    private val uid: String?
        get() = auth.currentUser?.uid

    fun getFavorites(onResult: (Result<List<String>>) -> Unit) {
        validateUid(onResult) {
            firestore.collection(FireCollection.FAVORITES)
                .document(uid!!)
                .get()
                .addOnSuccessListener { document ->
                    val favorite = document.toObject(Favorite::class.java)
                    onResult(Result.success(favorite?.games ?: emptyList()))
                }
                .addOnFailureListener { exception ->
                    onResult(Result.failure(exception))
                }
        }
    }

    fun addGameToFavorites(gameId: String, onResult: (Result<List<String>>) -> Unit) {
        updateFavorites(gameId, onResult) { favorite, id ->
            favorite.games + id
        }
    }

    fun removeGameFromFavorites(gameId: String, onResult: (Result<List<String>>) -> Unit) {
        updateFavorites(gameId, onResult) { favorite, id ->
            favorite.games.toMutableList().apply { remove(id) }
        }
    }

    private fun updateFavorites(
        gameId: String,
        onResult: (Result<List<String>>) -> Unit,
        update: (Favorite, String) -> List<String>
    ) {
        validateUid(onResult) {
            val favoritesRef = firestore.collection(FireCollection.FAVORITES)
                .document(uid!!)

            firestore.runTransaction { transaction ->
                val snapshot = transaction.get(favoritesRef)
                val favorite = snapshot.toObject(Favorite::class.java) ?: Favorite(emptyList())
                val updatedFavorites = update(favorite, gameId)
                transaction.set(favoritesRef, Favorite(updatedFavorites))
            }.addOnSuccessListener {
                getFavorites(onResult)
            }.addOnFailureListener { exception ->
                onResult(Result.failure(exception))
            }
        }
    }

    private fun validateUid(
        onResult: (Result<List<String>>) -> Unit,
        action: () -> Unit
    ) {
        if (uid == null) {
            onResult(Result.failure(Exception()))
        } else {
            action()
        }
    }
}
