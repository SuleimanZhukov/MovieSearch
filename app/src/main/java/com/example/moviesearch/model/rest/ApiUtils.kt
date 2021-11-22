package com.example.moviesearch.model.rest

object ApiUtils {
    private val baseUrlMainPart = "https://api.themoviedb.org/"
    private val baseUrlVersion = "3/search/movie?api_key="
    private val baseUrl = "$baseUrlMainPart$baseUrlVersion"
}