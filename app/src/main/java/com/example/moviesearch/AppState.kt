package com.example.moviesearch

import com.example.moviesearch.model.entities.NowPlayingMovie

sealed class AppState {

    data class Success(val MoviesData: List<NowPlayingMovie>) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()

}