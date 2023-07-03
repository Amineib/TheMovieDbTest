package io.dvlt.themoviedbtest.domain.usecase

import io.dvlt.themoviedbtest.domain.model.Movie
import io.dvlt.themoviedbtest.domain.model.Resource
import io.dvlt.themoviedbtest.domain.repository.MovieRepository
import io.dvlt.themoviedbtest.domain.truncate
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart

class GetTrendingUseCase constructor(
    private val repository: MovieRepository
) {
    // Retrieves the trending movies as a Flow of Resource<List<Movie>>
    fun getTrending(): Flow<Resource<List<Movie>>> = flow {
        emit(
            try {
                // Try to fetch the trending movies from the repository
                val result = repository.getTrendingMovies().map {
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
        // Emit the loading state as the initial value
        emit(Resource.Loading)
    }
}