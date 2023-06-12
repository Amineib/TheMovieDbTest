package io.dvlt.themoviedbtest.data.repository

import dagger.hilt.android.scopes.ActivityScoped
import io.dvlt.themoviedbtest.data.mappers.toDomain
import io.dvlt.themoviedbtest.data.remote.TmdbApi
import io.dvlt.themoviedbtest.domain.model.Movie
import io.dvlt.themoviedbtest.domain.repository.MovieRepository
import javax.inject.Inject

@ActivityScoped
class MovieRepositoryImp @Inject constructor(
    private val api: TmdbApi
) : MovieRepository {
    suspend override fun getTopRatedMovies(): List<Movie> {
        return api.getTopRatedMovies().results.map {
            it.toDomain()
        }
    }

    override suspend fun getTrendingMovies(): List<Movie> {
        return api.getTrending().results.map {
            it.toDomain()
        }
    }

    override suspend fun getMovieDetail(movieId: Int): Movie {
        return api.getMovieDetails(movieId).toDomain()
    }
}