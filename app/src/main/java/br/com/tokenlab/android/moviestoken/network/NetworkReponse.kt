package br.com.tokenlab.android.moviestoken.network

import java.io.IOException

sealed class NetworkReponse<out T : Any, out U : Any> {
    data class Success<T : Any>(val body: T) : NetworkReponse<T, Nothing>()
    data class ApiError<U : Any>(val body: U, val  code: Int) : NetworkReponse<Nothing, U>()
    data class NetworkError(val error: IOException) : NetworkReponse<Nothing, Nothing>()
    data class UnknownError(val error: Throwable? = null) : NetworkReponse<Nothing, Nothing>()
}