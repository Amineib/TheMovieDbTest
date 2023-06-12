package io.dvlt.themoviedbtest.domain.model

data class Movie(
    val id: Int,
    val posterPath: String,
    val title: String,
    val voteAverage: Float,
    val synopsys: String,
    val voteCount: Float
)
