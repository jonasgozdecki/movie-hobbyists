package com.arctouch.codechallenge.domain
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UpcomingMovies(val page: Int, val results: List<UpcomingMovie>) {

    data class UpcomingMovie(val id: Int,
                             @SerializedName("original_title") val name: String,
                             @SerializedName("backdrop_path") val imagePath: String,
                             @SerializedName("genre_ids") val genre: List<Int>,
                             @SerializedName("release_date") val releaseDate : String,
                             var genres: List<Genre>) : Serializable
}
