package io.dvlt.themoviedbtest.presentation.mappers

import io.dvlt.themoviedbtest.BuildConfig
import io.dvlt.themoviedbtest.domain.model.Movie
import io.dvlt.themoviedbtest.domain.truncate
import io.dvlt.themoviedbtest.presentation.model.MovieItemUi
import io.dvlt.themoviedbtest.presentation.screens.details.model.MovieDetailUi

fun Movie.toUi() = MovieItemUi (
    id = id,
    posterPath = BuildConfig.baseImageUrl + posterPath,
    title = title,
    voteAverage = voteAverage.toString()
)

fun Movie.toMovieDetailsUi() = MovieDetailUi (
    id = id.toString(),
    moviePoster = BuildConfig.baseImageUrl + posterPath,
    title = title,
    averageUserRating = voteAverage.truncate().toString(),
    synopsys = synopsys,
    voteCount = voteCount.toString()
)