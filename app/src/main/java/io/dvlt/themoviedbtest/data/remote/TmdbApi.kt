package io.dvlt.themoviedbtest.data.remote

import io.dvlt.themoviedbtest.data.model.MovieResponse
import io.dvlt.themoviedbtest.data.model.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbApi {

    // Retrieves a list of top-rated movies
    @GET("movie/top_rated?language=en-US")
    suspend fun getTopRatedMovies(
        @Query("page") page: Int = 1
    ): MoviesResponse

    // Retrieves a list of trending movies
    @GET("trending/all/day?language=en-US")
    suspend fun getTrending(
        @Query("page") page: Int = 1
    ): MoviesResponse

    // Retrieves details of a specific movie based on its ID
    @GET("movie/{movieId}")
    suspend fun getMovieDetails(@Path("movieId") movieId: Int): MovieResponse
}