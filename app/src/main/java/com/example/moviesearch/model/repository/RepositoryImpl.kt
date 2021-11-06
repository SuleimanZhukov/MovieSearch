package com.example.moviesearch.model.repository

import com.example.moviesearch.model.MovieLoader
import com.example.moviesearch.model.entities.Movie
import com.example.moviesearch.model.entities.getNowPlayingMovies
import com.example.moviesearch.model.entities.getUpcomingMovies

class RepositoryImpl : Repository {
    override fun getTheMovieFromServer(title: String): Movie {
        val dto = MovieLoader.loadMovie(title)
        return Movie(
            title = dto?.title ?: "Title",
            originalTitle = dto?.original_title ?: "Название",
            releaseDate = dto?.release_date ?: "0-0-0",
            voteAverage = dto?.vote_average ?: 0.0f,
        )
    }

    override fun getNowPlayingMoviesFromLocalSource(): List<Movie> {
        return getNowPlayingMovies()
    }

    override fun getUpcomingMoviesFromLocalSource(): List<Movie> {
        return getUpcomingMovies()
    }
}