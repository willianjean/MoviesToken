package br.com.tokenlab.android.moviestoken.network

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Converter
import java.lang.reflect.Type

class NetworkResponseApter<S : Any, E : Any>(
    private val successType: Type,
    private val errorBodyConverter: Converter<ResponseBody, E>
) : CallAdapter<S, Call<NetworkReponse<S, E>>>{
    override fun adapt(call: Call<S>): Call<NetworkReponse<S, E>> {
        return NetworkReponseCall(call, errorBodyConverter)
    }

    override fun responseType(): Type = successType

}