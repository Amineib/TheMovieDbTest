package io.dvlt.themoviedbtest.presentation.screens.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.dvlt.themoviedbtest.presentation.model.MovieItemUi
import io.dvlt.themoviedbtest.presentation.screens.components.ErrorScreen
import io.dvlt.themoviedbtest.presentation.screens.components.LoadingScreen
import io.dvlt.themoviedbtest.presentation.screens.components.ShowToast

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(), modifier: Modifier = Modifier,
    onMovieClicked: (Int) -> Unit
) {
    val topRatedState by viewModel.topRatedMovieState.collectAsStateWithLifecycle()
    val trendingState by viewModel.trendingMoviesState.collectAsStateWithLifecycle()

    HorizontalPager(modifier = modifier.fillMaxWidth(), pageCount = 2) { page ->
        when (page) {
            0 -> GenericMoviePager(movies = topRatedState.movies,
                isLoading = topRatedState.isLoading,
                errorMessage = topRatedState.errorMessage,
                onError = {
                    viewModel.loadData()
                }, onMovieClicked = onMovieClicked
            )

            1 -> GenericMoviePager(movies = trendingState.movies,
                isLoading = trendingState.isLoading,
                errorMessage = trendingState.errorMessage,
                onError = {
                    viewModel.loadData()
                }, onMovieClicked = onMovieClicked
            )
        }
    }

}


@Composable
fun GenericMoviePager(
    isLoading: Boolean, errorMessage: String, movies: List<MovieItemUi>, onError: () -> Unit,
    onMovieClicked: (Int) -> Unit
) {
    when {
        movies.isNotEmpty() -> {
            LazyVerticalGrid(
                modifier = Modifier.fillMaxWidth(), columns = GridCells.Fixed(2), state = rememberLazyGridState()
            ) {
                items(movies) {
                    MovieItem(
                        movie = it, onClick = {
                            onMovieClicked(it)
                        }, modifier = Modifier.height(250.dp)
                    )
                }
            }
        }

        isLoading -> LoadingScreen()
        !errorMessage.isEmpty() -> {
            ErrorScreen(onClick = onError)
            ShowToast(message = errorMessage)
        }
    }
}