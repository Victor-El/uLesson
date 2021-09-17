package me.codeenzyme.data.models

sealed class Resource<T> {
    class Error<T>: Resource<T>()
    data class Success<T>(val data: T): Resource<T>()
}
