package com.example.moviesearch.model.restentities

data class results(
    val original_title: String = "Original Title",
    val title: String = "Название",
    val release_date: String = "0-0-0",
    val vote_average: Float = 0.0f,
    val poster_path: String = "null",
    val overview: String = "Description"
)