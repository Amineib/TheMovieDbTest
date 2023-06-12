package io.dvlt.themoviedbtest.data.model

import com.google.gson.annotations.SerializedName

data class MoviesResponse(
    val page: Int,
    val results: List<MovieResponse>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)

data class MovieResponse(
    val id: Int,
    @SerializedName("original_title")
    val movieTitle: String?,
    @SerializedName("vote_average")
    val voteAverage: Float?,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("vote_count")
    val voteCount: Float?,
    @SerializedName("overview")
    val synopsys: String?
)