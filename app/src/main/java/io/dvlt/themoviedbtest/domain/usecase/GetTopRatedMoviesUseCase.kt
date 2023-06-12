package io.dvlt.themoviedbtest.domain.usecase

import io.dvlt.themoviedbtest.domain.model.Movie
import io.dvlt.themoviedbtest.domain.model.Resource
import io.dvlt.themoviedbtest.domain.repository.MovieRepository
import io.dvlt.themoviedbtest.domain.truncate
import retrofit2.HttpException
import java.io.IOException
import java.text.DecimalFormat

class GetTopRatedMoviesUseCase constructor(
    private val repository: MovieRepository
) {
    suspend fun getTopRatedMovies(): Resource<List<Movie>> {
        return try {
            val result = repository.getTopRatedMovies().map {
                Movie(
                    id = it.id,
                    posterPath = it.posterPath,
                    title = it.title,
                    voteAverage = it.voteAverage.truncate()
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
    }
}
