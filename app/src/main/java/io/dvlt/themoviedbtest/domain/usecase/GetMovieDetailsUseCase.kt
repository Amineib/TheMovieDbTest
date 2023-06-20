package io.dvlt.themoviedbtest.domain.usecase

import io.dvlt.themoviedbtest.domain.model.Movie
import io.dvlt.themoviedbtest.domain.model.Resource
import io.dvlt.themoviedbtest.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    fun getMovieDetails(movieId: Int): Flow<Resource<Movie>> = flow {
        emit(
            try {
                Resource.Success(repository.getMovieDetail(movieId))
            } catch (e: HttpException) {
                Resource.Error("An unexpected network error happened..")
            } catch (e: IOException) {
                Resource.Error("Couldn't reach server, please check your internet connection")
            } catch (e: Throwable) {
                Resource.Error("Unexpected error..")
            }
        )
    }.onStart { emit(Resource.Loading) }
}