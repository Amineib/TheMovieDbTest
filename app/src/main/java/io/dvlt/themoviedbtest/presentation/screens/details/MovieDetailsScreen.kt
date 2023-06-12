package io.dvlt.themoviedbtest.presentation.screens.details

import BackdropImage
import MovieDetailsBar
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.dvlt.themoviedbtest.presentation.screens.components.ErrorScreen
import io.dvlt.themoviedbtest.presentation.screens.components.LoadingScreen
import io.dvlt.themoviedbtest.presentation.screens.components.ShowToast

@Composable
fun MovieDetailsScreen(
    viewModel: MovieDetailsViewModel = hiltViewModel(),
    movieId: Int
) {
    val movieDetailState by viewModel.movieDetailsUiState.collectAsStateWithLifecycle()

    when {
        movieDetailState.movieDetails != null -> {
            movieDetailState.movieDetails?.let {
                MovieDetails(
                    movieTitle = it.title,
                    overview = it.synopsys,
                    poster = it.moviePoster,
                    voteCount = it.voteCount,
                    voteAverage = it.averageUserRating
                )
            }

        }

        movieDetailState.isLoading -> {
            LoadingScreen()
        }

        !movieDetailState.errorMessage.isEmpty() -> {
            ErrorScreen(onClick = {
                viewModel.loadMovieDetails()
            })
            ShowToast(message = movieDetailState.errorMessage)
        }
    }
}

@Composable
fun MovieDetails(
    movieTitle: String,
    overview: String,
    poster: String,
    voteCount: String,
    voteAverage: String,
    modifier: Modifier = Modifier
) {
    LazyColumn(state = rememberLazyListState(), modifier = modifier) {
        item {
            BackdropImage(moviePoster = poster)
        }

        item {
            Spacer(modifier = Modifier.height(2.dp))
        }

        item {
            MovieDetailsBar(
                image = poster,
                title = movieTitle,
                note = voteAverage,
                synopsys = overview,
                voteCount = voteCount
            )
        }
    }
}

