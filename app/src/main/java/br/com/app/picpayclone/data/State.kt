package br.com.app.picpayclone.data

sealed class State<T> {
    class Loading<T>: State<T>()
    class Success<T>(val data: T): State<T>()
    class Error<T>(val erro: Exception): State<T>()
}
