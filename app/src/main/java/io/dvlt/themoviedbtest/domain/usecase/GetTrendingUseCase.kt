package io.dvlt.themoviedbtest.domain.usecase

import io.dvlt.themoviedbtest.domain.model.Movie
import io.dvlt.themoviedbtest.domain.model.Resource
import io.dvlt.themoviedbtest.domain.repository.MovieRepository
import io.dvlt.themoviedbtest.domain.truncate
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import retrofit2.HttpException
import java.io.IOException
class GetTrendingUseCase constructor(
    private val repository: MovieRepository
) {


    fun getTrending(): Flow<Resource<List<Movie>>> = flow {
        emit(
            try {
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
            } catch (e: HttpException) {
                Resource.Error(e.localizedMessage ?: "An unexpected network error happened..")
            } catch (e: IOException) {
                Resource.Error("Couldn't reach server, please check your internet connection")
            } catch (e: Throwable) {
                Resource.Error("Unexpected error..")
            }
        )
    }.onStart {
        emit(Resource.Loading)
    }
}