package io.dvlt.themoviedbtest.presentation.screens.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.dvlt.themoviedbtest.domain.model.Resource
import io.dvlt.themoviedbtest.domain.usecase.GetMovieDetailsUseCase
import io.dvlt.themoviedbtest.presentation.mappers.toMovieDetailsUi
import io.dvlt.themoviedbtest.presentation.mappers.toUi
import io.dvlt.themoviedbtest.presentation.screens.details.model.MovieDetailUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase
) : ViewModel() {

    private val movieId: Int = checkNotNull(savedStateHandle["movieId"])

    private val _movieDetailsUiState = MutableStateFlow(MovieDetailUiState())
    val movieDetailsUiState: StateFlow<MovieDetailUiState> = _movieDetailsUiState


    init {
        loadMovieDetails()
    }

    fun loadMovieDetails() {
        viewModelScope.launch {
            getMovieDetailsUseCase.getMovieDetails(movieId).collectLatest { result ->
                when (result) {
                    is Resource.Error -> {
                        _movieDetailsUiState.emit(
                            MovieDetailUiState(errorMessage = result.message)
                        )
                    }

                    Resource.Loading -> {
                        _movieDetailsUiState.emit(
                            MovieDetailUiState(isLoading = true)
                        )
                    }

                    is Resource.Success -> {
                        _movieDetailsUiState.emit(
                            MovieDetailUiState(movieDetails = result.data.toMovieDetailsUi())
                        )
                    }
                }
            }
        }
    }

}