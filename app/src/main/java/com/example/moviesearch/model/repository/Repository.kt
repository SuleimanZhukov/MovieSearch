package com.example.moviesearch.model.repository

import com.example.moviesearch.model.entities.Movie

interface Repository {
    fun getTheMovieFromServer(title: String): Movie

    fun getNowPlayingMoviesFromLocalSource(): List<Movie>

    fun getUpcomingMoviesFromLocalSource(): List<Movie>
}