package io.dvlt.themoviedbtest

import io.dvlt.themoviedbtest.domain.model.Movie
import io.dvlt.themoviedbtest.domain.model.Resource
import io.dvlt.themoviedbtest.domain.repository.MovieRepository
import io.dvlt.themoviedbtest.domain.usecase.GetTopRatedMoviesUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GetTopRatedMoviesUseCaseTest {

    private lateinit var repository: MovieRepository
    private lateinit var useCase: GetTopRatedMoviesUseCase

    @Before
    fun setup() {
        repository = mockk()
        useCase = GetTopRatedMoviesUseCase(repository)
    }

    @Test
    fun `getTopRatedMovies should return loading and success`() = runTest {
        // Arrange
        val movies = listOf(
            Movie(1, "Movie 1", "poster1.jpg", 7.5f, synopsys = "Synopsys", voteCount = 245f),
            Movie(2, "Movie 2", "poster2.jpg", 8.2f, synopsys = "Synopsys", voteCount = 245f)
        )
        coEvery { repository.getTopRatedMovies() } returns movies

        // Act
        val result = useCase.getTopRatedMovies().take(2).drop(1).first()

        // Assert
        assertEquals(Resource.Success(movies), result)
    }

}
