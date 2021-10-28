package com.example.moviesearch

import com.example.moviesearch.model.entities.Movie

sealed class AppState {

    data class Success(val moviesData: List<Movie>) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()

}