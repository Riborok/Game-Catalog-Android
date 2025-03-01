package com.bsuir.game_catalog.repository

import com.bsuir.game_catalog.model.ReviewRequest
import com.bsuir.game_catalog.model.Review
import com.bsuir.game_catalog.model.UserProfile
import com.bsuir.game_catalog.utils.FireCollection
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.FirebaseFirestore

class ReviewRepository {

    private val firestore = FirebaseFirestore.getInstance()
    private val repository = ProfileRepository()

    fun createReview(
        request: ReviewRequest,
        onResult: (Result<Review>) -> Unit
    ) {
        addReviewToFirestore(request) { result ->
            result.onSuccess { document ->
                repository.getUserProfile(request.userId) { profileResult ->
                    profileResult.onSuccess { profile ->
                        val reviewResponse = buildReviewResponse(request, profile)
                        onResult(Result.success(reviewResponse))
                    }
                    profileResult.onFailure { ex ->
                        onResult(Result.failure(ex))
                    }
                }
            }
            result.onFailure { ex ->
                onResult(Result.failure(ex))
            }
        }
    }

    private fun addReviewToFirestore(
        request: ReviewRequest,
        callback: (Result<DocumentSnapshot>) -> Unit
    ) {
        firestore.collection(FireCollection.REVIEWS)
            .add(request)
            .addOnSuccessListener { docRef ->
                docRef.get().addOnSuccessListener { document ->
                    callback(Result.success(document))
                }.addOnFailureListener { ex ->
                    callback(Result.failure(ex))
                }
            }
            .addOnFailureListener { ex ->
                callback(Result.failure(ex))
            }
    }


    private fun buildReviewResponse(
        request: ReviewRequest,
        profile: UserProfile
    ): Review {
        return Review(
            user = profile,
            gameId = request.gameId,
            rating = request.rating,
            text = request.text
        )
    }

    fun getReviewsForGame(
        gameId: String,
        onResult: (Result<List<Review>>) -> Unit
    ) {
        fetchReviews(gameId) { reviewResult ->
            reviewResult.onSuccess { reviewList ->
                fetchUserProfiles(reviewList) { profileResult ->
                    profileResult.onSuccess { profilesMap ->
                        val reviewResponses = buildReviewResponses(reviewList, profilesMap)
                        onResult(Result.success(reviewResponses))
                    }
                    profileResult.onFailure { exception ->
                        onResult(Result.failure(exception))
                    }
                }
            }
            reviewResult.onFailure { exception ->
                onResult(Result.failure(exception))
            }
        }
    }

    private fun fetchReviews(
        gameId: String,
        callback: (Result<List<ReviewRequest>>) -> Unit
    ) {
        firestore.collection(FireCollection.REVIEWS)
            .whereEqualTo("gameId", gameId)
            .get()
            .addOnSuccessListener { querySnapshot ->
                val reviewList = querySnapshot.documents.mapNotNull { doc ->
                    doc.toObject(ReviewRequest::class.java)
                }
                callback(Result.success(reviewList))
            }
            .addOnFailureListener { exception ->
                callback(Result.failure(exception))
            }
    }

    private fun fetchUserProfiles(
        reviewList: List<ReviewRequest>,
        callback: (Result<Map<String, UserProfile>>) -> Unit
    ) {
        val userIds = reviewList.map { it.userId }.distinct()
        if (userIds.isEmpty()) {
            callback(Result.success(emptyMap()))
            return
        }
        firestore.collection(FireCollection.USERS)
            .whereIn(FieldPath.documentId(), userIds)
            .get()
            .addOnSuccessListener { profilesSnapshot ->
                val profilesMap = profilesSnapshot.documents.associate { doc ->
                    doc.id to (doc.toObject(UserProfile::class.java) ?: UserProfile())
                }
                callback(Result.success(profilesMap))
            }
            .addOnFailureListener { exception ->
                callback(Result.failure(exception))
            }
    }

    private fun buildReviewResponses(
        reviewList: List<ReviewRequest>,
        profilesMap: Map<String, UserProfile>
    ): List<Review> {
        return reviewList.map { review ->
            val profile = profilesMap[review.userId] ?: UserProfile()
            Review(
                user = profile,
                gameId = review.gameId,
                rating = review.rating,
                text = review.text
            )
        }
    }
}
