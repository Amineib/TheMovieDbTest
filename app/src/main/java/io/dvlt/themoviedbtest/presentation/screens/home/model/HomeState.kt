package io.dvlt.themoviedbtest.presentation.screens.home.model

import io.dvlt.themoviedbtest.presentation.model.MovieItemUi

data class PagerUiState(
    val movies: List<MovieItemUi> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String = ""
)