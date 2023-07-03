package io.dvlt.themoviedbtest.domain.usecase

import io.dvlt.themoviedbtest.domain.model.Movie
import io.dvlt.themoviedbtest.domain.model.Resource
import io.dvlt.themoviedbtest.domain.repository.MovieRepository
import io.dvlt.themoviedbtest.domain.truncate
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart

class GetTopRatedMoviesUseCase constructor(
    private val repository: MovieRepository
) {
    fun getTopRatedMovies(): Flow<Resource<List<Movie>>> = flow {
        emit(
            try {
                val result = repository.getTopRatedMovies().map {
                    Movie(
                        id = it.id,
                        posterPath = it.posterPath,
                        title = it.title,
                        voteAverage = it.voteAverage.truncate(),
                        synopsys = it.synopsys,
                        voteCount = it.voteCount
                    )
                }
                Resource.Success(result)
            } catch (e: Exception) {
                Resource.Error(e)
            }
        )
    }.onStart {
        emit(
            Resource.Loading
        )
    }
}
