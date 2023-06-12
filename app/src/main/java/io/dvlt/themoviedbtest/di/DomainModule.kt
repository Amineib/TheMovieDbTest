package io.dvlt.themoviedbtest.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.dvlt.themoviedbtest.data.remote.TmdbApi
import io.dvlt.themoviedbtest.data.repository.MovieRepositoryImp
import io.dvlt.themoviedbtest.domain.repository.MovieRepository
import io.dvlt.themoviedbtest.domain.usecase.GetMovieDetailsUseCase
import io.dvlt.themoviedbtest.domain.usecase.GetTopRatedMoviesUseCase
import io.dvlt.themoviedbtest.domain.usecase.GetTrendingUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Singleton
    @Provides
    fun provideMovieRepository(api: TmdbApi): MovieRepository = MovieRepositoryImp(api)

    @Singleton
    @Provides
    fun provideGetTopRatedMoviesUseCase(repository: MovieRepository) = GetTopRatedMoviesUseCase(repository)

    @Singleton
    @Provides
    fun provideGetMovieDetailsUseCase(repository: MovieRepository) = GetMovieDetailsUseCase(repository)

    @Singleton
    @Provides
    fun provideGetTrendingUseCase(repository: MovieRepository) = GetTrendingUseCase(repository)
}