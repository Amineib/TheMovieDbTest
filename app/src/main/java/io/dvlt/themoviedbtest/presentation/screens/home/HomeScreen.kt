package io.dvlt.themoviedbtest.presentation.screens.home

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberImagePainter
import io.dvlt.themoviedbtest.R
import io.dvlt.themoviedbtest.presentation.model.MovieItemUi
import io.dvlt.themoviedbtest.presentation.screens.components.ErrorScreen
import io.dvlt.themoviedbtest.presentation.screens.components.LoadingScreen
import io.dvlt.themoviedbtest.presentation.screens.components.ShowToast

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(), modifier: Modifier = Modifier
) {

    val topRatedState by viewModel.topRatedMovieState.collectAsStateWithLifecycle()
    val trendingState by viewModel.trendingMoviesState.collectAsStateWithLifecycle()

    HorizontalPager(modifier = modifier.fillMaxWidth(), pageCount = 2) { page ->
        when (page) {
            0 -> MoviesPager(
                movies = topRatedState.movies,
                isLoading = topRatedState.isLoading,
                errorMessage = topRatedState.errorMessage
            )

            1 -> MoviesPager(
                movies = trendingState.movies,
                isLoading = trendingState.isLoading,
                errorMessage = trendingState.errorMessage
            )
        }
    }

}


@Composable
fun MoviesPager(
    isLoading: Boolean, errorMessage: String, movies: List<MovieItemUi>
) {
    when {
        movies.isNotEmpty() -> {
            LazyVerticalGrid(
                modifier = Modifier.fillMaxWidth(), columns = GridCells.Fixed(2), state = rememberLazyGridState()
            ) {
                items(movies) {
                    //listItem()
                    MovieItem(
                        movie = it, onClick = {

                        },
                        modifier = Modifier.height(250.dp)
                    )
                }
            }
        }

        isLoading -> LoadingScreen()
        !errorMessage.isEmpty() -> {
            ErrorScreen()
            ShowToast(message = errorMessage)
        }
    }
}


@Composable
fun MovieListItem(
    movie: MovieItemUi, onClick: (Int) -> Unit, modifier: Modifier = Modifier
) {

    Column(modifier = modifier.clickable {
        onClick(movie.id)
    }) {
        val painter = rememberImagePainter(data = movie.posterPath, builder = {
            crossfade(1000)
            error(R.drawable.ic_search)
        })
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier
                .size(120.dp)
                .shadow(5.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = movie.title,
            style = TextStyle(fontWeight = FontWeight.Bold),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "Average Count: ${movie.voteAverage}",
            style = TextStyle(fontStyle = FontStyle.Italic),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
        )
    }
}

