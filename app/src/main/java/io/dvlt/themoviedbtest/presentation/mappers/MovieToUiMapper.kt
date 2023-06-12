package io.dvlt.themoviedbtest.presentation.mappers

import io.dvlt.themoviedbtest.BuildConfig
import io.dvlt.themoviedbtest.domain.model.Movie
import io.dvlt.themoviedbtest.presentation.model.MovieItemUi

fun Movie.toUi() = MovieItemUi (
    id = id,
    posterPath = BuildConfig.baseImageUrl + posterPath,
    title = title,
    voteAverage = voteAverage.toString()
)