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
) : Call<NetworkReponse<S, E>>{
    override fun enqueue(callback: Callback<NetworkReponse<S, E>>) {
        return delegate.enqueue(object : Callback<S>{
            override fun onResponse(call: Call<S>, response: Response<S>) {
                val body = response.body()
                val code = response.code()
                val error = response.errorBody()

                if (response.isSuccessful){
                    if(body != null){
                        callback.onResponse(
                            this@NetworkReponseCall,
                            Response.success(NetworkReponse.Success(body))
                        )
                    }else{
                        callback.onResponse(
                            this@NetworkReponseCall,
                            Response.success(NetworkReponse.UnknownError())
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
                            Response.success(NetworkReponse.ApiError(errorBody, code))
                        )
                    }else{
                        callback.onResponse(
                            this@NetworkReponseCall,
                            Response.success(NetworkReponse.UnknownError())
                        )
                    }
                }
            }

            override fun onFailure(call: Call<S>, t: Throwable) {
                val networkReponse = when (t){
                    is IOException -> NetworkReponse.NetworkError(t)
                    else -> NetworkReponse.UnknownError()
                }
                callback.onResponse(
                    this@NetworkReponseCall,
                    Response.success(networkReponse)
                )
            }

        })
    }

    override fun clone(): Call<NetworkReponse<S, E>> = NetworkReponseCall(delegate.clone(), errorConverter)

    override fun execute(): Response<NetworkReponse<S, E>> {
        throw UnsupportedOperationException("NetworkResponseCall doesn't support execute")
    }

    override fun isExecuted(): Boolean = delegate.isExecuted

    override fun cancel() = delegate.cancel()

    override fun isCanceled(): Boolean = delegate.isCanceled

    override fun request(): Request = delegate.request()

    override fun timeout(): Timeout = delegate.timeout()

}