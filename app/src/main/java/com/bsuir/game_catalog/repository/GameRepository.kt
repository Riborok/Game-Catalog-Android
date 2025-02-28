package com.bsuir.game_catalog.repository

import com.bsuir.game_catalog.model.Game
import com.bsuir.game_catalog.utils.FireCollection
import com.google.firebase.firestore.FirebaseFirestore

class GameRepository {

    private val firestore = FirebaseFirestore.getInstance()

    fun getAllGames(onResult: (Result<List<Game>>) -> Unit) {
        firestore.collection(FireCollection.GAMES)
            .get()
            .addOnSuccessListener { result ->
                val games = result.documents.mapNotNull { doc ->
                    doc.toObject(Game::class.java)?.copy(id = doc.id)
                }
                onResult(Result.success(games))
            }
            .addOnFailureListener { exception ->
                onResult(Result.failure(exception))
            }
    }
}