package com.bsuir.game_catalog.repository

import com.bsuir.game_catalog.model.ReviewRequest
import com.bsuir.game_catalog.model.ReviewResponse
import com.bsuir.game_catalog.model.UserProfile
import com.bsuir.game_catalog.utils.FireCollection
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.FirebaseFirestore

class ReviewRepository {

    private val firestore = FirebaseFirestore.getInstance()
    private val repository = ProfileRepository()

    fun createReview(
        request: ReviewRequest,
        onResult: (Result<ReviewResponse>) -> Unit
    ) {
        firestore.collection("reviews")
            .add(request)
            .addOnSuccessListener { docRef ->
                docRef.get().addOnSuccessListener { document ->
                    repository.getUserProfile(request.userId) { profileResult ->
                        profileResult.onSuccess { profile ->
                            val reviewResponse = ReviewResponse(
                                user = profile,
                                gameId = request.gameId,
                                rating = request.rating,
                                text = request.text
                            )
                            onResult(Result.success(reviewResponse))
                        }
                        profileResult.onFailure { ex ->
                            onResult(Result.failure(ex))
                        }
                    }
                }.addOnFailureListener { ex ->
                    onResult(Result.failure(ex))
                }
            }
            .addOnFailureListener { exception ->
                onResult(Result.failure(exception))
            }
    }

    fun getReviewsForGame(
        gameId: String,
        onResult: (Result<List<ReviewResponse>>) -> Unit
    ) {
        firestore.collection("reviews")
            .whereEqualTo("gameId", gameId)
            .get()
            .addOnSuccessListener { querySnapshot ->
                val reviewDocs = querySnapshot.documents
                val reviewList = reviewDocs.mapNotNull { doc ->
                    doc.toObject(ReviewRequest::class.java)
                }
                val userIds = reviewList.map { it.userId }.distinct()
                if (userIds.isEmpty()) {
                    onResult(Result.success(emptyList()))
                    return@addOnSuccessListener
                }
                firestore.collection(FireCollection.USERS)
                    .whereIn(FieldPath.documentId(), userIds)
                    .get()
                    .addOnSuccessListener { profilesSnapshot ->
                        val profilesMap = profilesSnapshot.documents.associate { doc ->
                            doc.id to (doc.toObject(UserProfile::class.java) ?: UserProfile())
                        }
                        val reviewResponses = reviewList.map { review ->
                            val profile = profilesMap[review.userId] ?: UserProfile()
                            ReviewResponse(
                                user = profile,
                                gameId = review.gameId,
                                rating = review.rating,
                                text = review.text
                            )
                        }
                        onResult(Result.success(reviewResponses))
                    }
                    .addOnFailureListener { exception ->
                        onResult(Result.failure(exception))
                    }
            }
            .addOnFailureListener { exception ->
                onResult(Result.failure(exception))
            }
    }
}
