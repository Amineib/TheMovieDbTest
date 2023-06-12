package io.dvlt.themoviedbtest.presentation.screens.details.model

data class MovieDetailUi(
    val id: String,
    val title: String,
    val averageUserRating: String,
    val voteCount: String,
    val moviePoster: String,
    val synopsys: String
)