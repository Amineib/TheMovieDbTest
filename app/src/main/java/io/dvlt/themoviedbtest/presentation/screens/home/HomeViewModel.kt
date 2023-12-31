package io.dvlt.themoviedbtest.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.dvlt.themoviedbtest.domain.model.Resource
import io.dvlt.themoviedbtest.domain.usecase.GetTopRatedMoviesUseCase
import io.dvlt.themoviedbtest.domain.usecase.GetTrendingUseCase
import io.dvlt.themoviedbtest.presentation.mappers.toUi
import io.dvlt.themoviedbtest.presentation.screens.home.model.PagerUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val topRatedMoviesUseCase: GetTopRatedMoviesUseCase, private val getTrendingUseCase: GetTrendingUseCase
) : ViewModel() {

    private val _topRatedMovieState = MutableStateFlow(PagerUiState())
    val topRatedMovieState: StateFlow<PagerUiState> = _topRatedMovieState

    private val _trendingMoviesState = MutableStateFlow(PagerUiState())
    val trendingMoviesState: StateFlow<PagerUiState> = _trendingMoviesState

    init {
        loadData()
    }

    private fun loadTopRated() {
        viewModelScope.launch {
            delay(1000)
            topRatedMoviesUseCase.getTopRatedMovies().collectLatest { result ->
                when (result) {
                    is Resource.Error -> {
                        _topRatedMovieState.emit(
                            PagerUiState(errorMessage = result.error.message.toString())
                        )
                    }

                    Resource.Loading -> {
                        _topRatedMovieState.emit(
                            PagerUiState(isLoading = true)
                        )
                    }

                    is Resource.Success -> {
                        _topRatedMovieState.emit(
                            PagerUiState(movies = result.data.map { it.toUi() })
                        )
                    }
                }
            }
        }
    }

    private fun loadTrending() {
        viewModelScope.launch {
            delay(1000)
            getTrendingUseCase.getTrending().collectLatest { result ->
                when (result) {
                    is Resource.Error -> {
                        _trendingMoviesState.emit(
                            PagerUiState(errorMessage = result.error.message.toString())
                        )
                    }

                    Resource.Loading -> {
                        _trendingMoviesState.emit(
                            PagerUiState(isLoading = true)
                        )
                    }

                    is Resource.Success -> {
                        _trendingMoviesState.emit(
                            PagerUiState(movies = result.data.map { it.toUi() })
                        )
                    }
                }
            }
        }
    }

    fun loadData() {
        loadTrending()
        loadTopRated()
    }
}