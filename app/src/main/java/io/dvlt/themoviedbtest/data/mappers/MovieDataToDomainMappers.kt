package io.dvlt.themoviedbtest.data.mappers

import io.dvlt.themoviedbtest.data.model.MovieResponse
import io.dvlt.themoviedbtest.domain.model.Movie

fun MovieResponse.toDomain() = Movie(
    id = id, posterPath = posterPath?.trim() ?: "" , title = movieTitle?.trim() ?: "", voteAverage = voteAverage ?: 0.toFloat()
)