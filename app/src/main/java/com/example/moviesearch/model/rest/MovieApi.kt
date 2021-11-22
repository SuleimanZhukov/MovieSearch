package com.example.moviesearch.model.rest

import com.example.moviesearch.model.restentities.MovieDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET("search/movie?api_key=")
    fun getMovie(
        @Query("@language=") language: String,
        @Query("@query") movieName: String
    ): Call<MovieDTO>
}