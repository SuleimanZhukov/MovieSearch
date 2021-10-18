package com.example.moviesearch.model.repository

interface Repository {
    fun getMoviesFromLocalSource()

    fun getMoviesFromServer()
}