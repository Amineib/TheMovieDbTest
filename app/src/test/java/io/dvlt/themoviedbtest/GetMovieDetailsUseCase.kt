package io.dvlt.themoviedbtest

import io.dvlt.themoviedbtest.domain.model.Movie
import io.dvlt.themoviedbtest.domain.model.Resource
import io.dvlt.themoviedbtest.domain.repository.MovieRepository
import io.dvlt.themoviedbtest.domain.usecase.GetMovieDetailsUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Test
import retrofit2.HttpException
import java.io.IOException

class GetMovieDetailsUseCaseTest {

    // Create a mock MovieRepository
    private val mockRepository: MovieRepository = mockk()

    // Create an instance of the use case with the mock repository
    private val useCase = GetMovieDetailsUseCase(mockRepository)

    @Test
    fun `getMovieDetails should return movie details successfully`() = runBlockingTest {
        // Mock the movie ID and movie object
        val movieId = 123
        val movie =
            Movie(
                id = movieId,
                title = "Test Movie",
                posterPath = "poster.jpg",
                voteAverage = 7.5f,
                synopsys = "stuff",
                voteCount = 2000f
            )

        // Mock the repository's getMovieDetail function to return the movie
        coEvery { mockRepository.getMovieDetail(movieId) } returns movie

        // Call the use case function
        val resultFlow = useCase.getMovieDetails(movieId)

        // Collect the result
        val result = resultFlow.first()

        // Verify that the result is a success and contains the correct movie
        Assert.assertTrue(result is Resource.Success)
        Assert.assertEquals(movie, (result as Resource.Success).data)

        // Verify that the repository's getMovieDetail function was called with the correct movie ID
        coVerify { mockRepository.getMovieDetail(movieId) }
    }

    @Test
    fun `getMovieDetails should return error on HTTP exception`() = runBlockingTest {
        // Mock the movie ID and an HTTP exception
        val movieId = 123
        val exception = mockk<HttpException>()

        // Mock the repository's getMovieDetail function to throw the exception
        coEvery { mockRepository.getMovieDetail(movieId) } throws exception

        // Call the use case function
        val resultFlow = useCase.getMovieDetails(movieId)

        // Collect the result
        val result = resultFlow.first()

        // Verify that the result is an error and contains the correct error message
        Assert.assertTrue(result is Resource.Error)
        Assert.assertEquals("An unexpected network error happened..", (result as Resource.Error).message)

        // Verify that the repository's getMovieDetail function was called with the correct movie ID
        coVerify { mockRepository.getMovieDetail(movieId) }
    }

    @Test
    fun `getMovieDetails should return error on IO exception`() = runBlockingTest {
        // Mock the movie ID and an IO exception
        val movieId = 123
        val exception = mockk<IOException>()

        // Mock the repository's getMovieDetail function to throw the exception
        coEvery { mockRepository.getMovieDetail(movieId) } throws exception

        // Call the use case function
        val resultFlow = useCase.getMovieDetails(movieId)

        // Collect the result
        val result = resultFlow.first()

        // Verify that the result is an error and contains the correct error message
        Assert.assertTrue(result is Resource.Error)
        Assert.assertEquals("Couldn't reach server, please check your internet connection",  (result as Resource.Error).message)

        // Verify that the repository's getMovieDetail function was called with the correct movie ID
        coVerify { mockRepository.getMovieDetail(movieId) }
    }

    @Test
    fun `getMovieDetails should return error on unexpected exception`() = runBlockingTest {
        // Mock the movie ID and an unexpected exception
        val movieId = 123
        val exception = mockk<Throwable>()

        // Mock the repository's getMovieDetail function to throw the exception
        coEvery { mockRepository.getMovieDetail(movieId) } throws exception

        // Call the use case function
        val resultFlow = useCase.getMovieDetails(movieId)

        // Collect the result
        val result = resultFlow.first()

        // Verify that the result is an error and contains the correct error message
        Assert.assertTrue(result is Resource.Error)
        Assert.assertEquals("Unexpected error..", (result as Resource.Error).message)

        // Verify that the repository's getMovieDetail function was called with the correct movie ID
        coVerify { mockRepository.getMovieDetail(movieId) }
    }
}
