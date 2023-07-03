package io.dvlt.themoviedbtest.data.mappers

import io.dvlt.themoviedbtest.data.model.MovieResponse
import io.dvlt.themoviedbtest.domain.model.Movie
import io.dvlt.themoviedbtest.domain.truncate

fun MovieResponse.toDomain() = Movie(
    id = id,
    posterPath = posterPath?.trim() ?: "",
    title = movieTitle?.trim() ?: "",
    voteAverage = voteAverage?.truncate() ?: 0.toFloat(),
    voteCount = voteCount ?: 0.toFloat(),
    synopsys = synopsys ?: ""
)