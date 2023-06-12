package io.dvlt.themoviedbtest.domain.usecase

import io.dvlt.themoviedbtest.domain.model.Movie
import io.dvlt.themoviedbtest.domain.repository.MovieRepository
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
    private val repository : MovieRepository
) {
    suspend fun getMovieDetails(movieId : Int) : Movie {
        return repository.getMovieDetail(movieId)
    }
}