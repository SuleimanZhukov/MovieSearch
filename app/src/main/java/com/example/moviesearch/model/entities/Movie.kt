package com.example.moviesearch.model.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val title: String = "Title",
    val poster: Int = 0,
    val date: String = "0/0/0",
    val rating: String = "0"
) : Parcelable

fun getNowPlayingMovies(): List<Movie> {
    return mutableListOf(
        Movie("Avengers", 0, "21/10/2019", "8.7"),
        Movie("Spider-Man", 1, "21/10/2019", "8.7"),
        Movie("Batman", 2, "21/10/2019", "8.7"),
        Movie("Guardians", 3, "21/10/2019", "8.7"),
        Movie("Venom", 4, "21/10/2019", "8.7"),
        Movie("Harry Potter", 5, "21/10/2019", "8.7")
    )
}

fun getUpcomingMovies(): List<Movie> {
    return mutableListOf(
        Movie("Avengers", 0, "21/10/2019"),
        Movie("Spider-Man", 1, "21/10/2019"),
        Movie("Batman", 2, "21/10/2019"),
        Movie("Guardians", 3, "21/10/2019"),
        Movie("Venom", 4, "21/10/2019"),
        Movie("Harry Potter", 5, "21/10/2019")
    )
}