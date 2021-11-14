package com.example.moviesearch.model.repository

import com.example.moviesearch.model.MovieLoader
import com.example.moviesearch.model.entities.Movie
import com.example.moviesearch.model.entities.getNowPlayingMovies
import com.example.moviesearch.model.entities.getUpcomingMovies

class RepositoryImpl : Repository {
    override fun getTheMovieFromServer(title: String): Movie {
        val dto = MovieLoader.loadMovie(title)
        return Movie(
            title = dto!!.results[0]?.title ?: "Title",
            originalTitle = dto!!.results[0]?.original_title ?: "Название",
            releaseDate = dto!!.results[0]?.release_date ?: "0-0-0",
            voteAverage = dto!!.results[0]?.vote_average ?: 0.0f,
            overview = dto!!.results[0]?.overview ?: "Description"
        )
    }

    override fun getNowPlayingMoviesFromLocalSource(): List<Movie> {
        return getNowPlayingMovies()
    }

    override fun getUpcomingMoviesFromLocalSource(): List<Movie> {
        return getUpcomingMovies()
    }
}