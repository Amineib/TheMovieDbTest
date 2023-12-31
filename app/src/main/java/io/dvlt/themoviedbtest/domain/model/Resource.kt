package io.dvlt.themoviedbtest.domain.model

sealed class Resource<out T: Any> {
    data class Success<out T: Any>(val data: T): Resource<T>()
    data class Error(val error: Throwable): Resource<Nothing>()
    object Loading: Resource<Nothing>()
}
