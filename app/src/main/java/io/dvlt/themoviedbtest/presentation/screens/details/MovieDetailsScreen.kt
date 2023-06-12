package io.dvlt.themoviedbtest.presentation.screens.details

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun MovieDetailsScreen(
    viewModel: MovieDetailsViewModel = hiltViewModel(),
    movieId: Int
) {
    val movieDetail by viewModel.movieDetailsUiState.collectAsStateWithLifecycle()
    
    Text(text = movieDetail.movieDetails.toString())
}