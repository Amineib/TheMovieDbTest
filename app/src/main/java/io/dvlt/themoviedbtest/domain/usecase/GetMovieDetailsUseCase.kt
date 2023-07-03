package io.dvlt.themoviedbtest.domain.usecase

import io.dvlt.themoviedbtest.domain.model.Movie
import io.dvlt.themoviedbtest.domain.model.Resource
import io.dvlt.themoviedbtest.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    fun getMovieDetails(movieId: Int): Flow<Resource<Movie>> = flow {
        emit(
            try {
                Resource.Success(repository.getMovieDetail(movieId))
            } catch (e: Exception) {
                Resource.Error(e)
            }
        )
    }.onStart { emit(Resource.Loading) }
}

