package com.bsuir.game_catalog.viewmodel

import android.app.Application
import com.bsuir.game_catalog.R
import com.bsuir.game_catalog.model.Review
import com.bsuir.game_catalog.model.ReviewRequest
import com.bsuir.game_catalog.repository.ReviewRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ReviewViewModel(application: Application) : StatusHandlingViewModel(application)  {

    private val repository = ReviewRepository()

    private val _reviews = MutableStateFlow<List<Review>>(emptyList())
    val reviews = _reviews.asStateFlow()

    fun createReview(request: ReviewRequest) {
        repository.createReview(request) { result ->
            result.onSuccess { newReview ->
                _isApproved.value = true
                _reviews.value += newReview
            }
            result.onFailure(R.string.review_create_failed)
        }
    }

    fun loadReviews(gameId: String) {
        repository.getReviewsForGame(gameId) { result ->
            result.onSuccess { reviewsList ->
                _reviews.value = reviewsList
            }
            result.onFailure(R.string.reviews_load_failed)
        }
    }

    private fun Result<*>.onFailure(errorStrId: Int) {
        onFailure { throwable ->
            val context = getApplication<Application>().applicationContext
            _errorMessage.value = throwable.message ?: context.getString(errorStrId)
        }
    }
}