package io.dvlt.themoviedbtest.domain.repository

import io.dvlt.themoviedbtest.domain.model.Movie

interface MovieRepository {
    suspend fun getTopRatedMovies(): List<Movie>
    suspend fun getTrendingMovies(): List<Movie>
    suspend fun getMovieDetail(movieId: Int): Movie
}