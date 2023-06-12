package io.dvlt.themoviedbtest.presentation.screens.details.model

data class MovieDetailUiState (
    val movieDetails : MovieDetailUi? = null,
    val isLoading: Boolean = false,
    val errorMessage: String = ""
)