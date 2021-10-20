package com.example.moviesearch.model.repository

import com.example.moviesearch.model.entities.Movie
import com.example.moviesearch.model.entities.getNowPlayingMovies
import com.example.moviesearch.model.entities.getUpcomingMovies

class RepositoryImpl : Repository {
    override fun getTheMovieFromServer(): Movie {
        return Movie()
    }

    override fun getNowPlayingMoviesFromLocalSource(): List<Movie> {
        return getNowPlayingMovies()
    }

    override fun getUpcomingMoviesFromLocalSource(): List<Movie> {
        return getUpcomingMovies()
    }
}