package br.com.tokenlab.android.moviestoken.network

import okhttp3.Request
import okhttp3.ResponseBody
import okio.IOException
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Converter
import retrofit2.Response
import java.lang.Exception
import java.lang.UnsupportedOperationException

internal class NetworkReponseCall <S : Any, E : Any> (
    private val delegate: Call<S>,
    private val errorConverter: Converter<ResponseBody, E>
) : Call<NetworkResponse<S, E>>{
    override fun enqueue(callback: Callback<NetworkResponse<S, E>>) {
        return delegate.enqueue(object : Callback<S>{
            override fun onResponse(call: Call<S>, response: Response<S>) {
                val body = response.body()
                val code = response.code()
                val error = response.errorBody()

                if (response.isSuccessful){
                    if(body != null){
                        callback.onResponse(
                            this@NetworkReponseCall,
                            Response.success(NetworkResponse.Success(body))
                        )
                    }else{
                        callback.onResponse(
                            this@NetworkReponseCall,
                            Response.success(NetworkResponse.UnknownError())
                        )
                    }
                }else{
                    val errorBody = when {
                        error == null -> null
                        error.contentLength() == 0L -> null
                        else -> try {
                            errorConverter.convert(error)
                        } catch (e: Exception){
                            null
                        }
                    }
                    if (errorBody != null){
                        callback.onResponse(
                            this@NetworkReponseCall,
                            Response.success(NetworkResponse.ApiError(errorBody, code))
                        )
                    }else{
                        callback.onResponse(
                            this@NetworkReponseCall,
                            Response.success(NetworkResponse.UnknownError())
                        )
                    }
                }
            }

            override fun onFailure(call: Call<S>, t: Throwable) {
                val networkReponse = when (t){
                    is IOException -> NetworkResponse.NetworkError(t)
                    else -> NetworkResponse.UnknownError()
                }
                callback.onResponse(
                    this@NetworkReponseCall,
                    Response.success(networkReponse)
                )
            }

        })
    }

    override fun clone(): Call<NetworkResponse<S, E>> = NetworkReponseCall(delegate.clone(), errorConverter)

    override fun execute(): Response<NetworkResponse<S, E>> {
        throw UnsupportedOperationException("NetworkResponseCall doesn't support execute")
    }

    override fun isExecuted(): Boolean = delegate.isExecuted

    override fun cancel() = delegate.cancel()

    override fun isCanceled(): Boolean = delegate.isCanceled

    override fun request(): Request = delegate.request()

    override fun timeout(): Timeout = delegate.timeout()

}