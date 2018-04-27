package com.arctouch.codechallenge.domain

import com.google.gson.annotations.SerializedName

data class MovieDetails(@SerializedName("original_title") val name: String,
                        @SerializedName("poster_path") val poster: String,
                        @SerializedName("release_date") val releaseDate: String,
                         val genres: List<Genre>,
                         val overview: String)

data class Genres(val genres: List<Genre>)

data class Genre(val id: Int, val name: String)