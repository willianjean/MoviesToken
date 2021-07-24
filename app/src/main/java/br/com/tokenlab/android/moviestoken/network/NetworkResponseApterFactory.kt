package br.com.tokenlab.android.moviestoken.network

import br.com.tokenlab.android.moviestoken.network.model.dto.MoviesResponseDTOItem
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class NetworkResponseApterFactory : CallAdapter.Factory() {
    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        // Call<NetworkReponse<List<MoviesResponseDTOItem>, ErrorResponse>>
        if (Call::class.java != getRawType(returnType)){
            return null
        }

        check(returnType is ParameterizedType){
            "return type must be parameterized as Call<NetworkResponse<something>>"
        }

        val responseType = getParameterUpperBound(0, returnType)

        if (getRawType(responseType) != NetworkReponse::class.java){
            return null
        }

        check(responseType is ParameterizedType){
            "return type must be parameterized as NetworkReponse<something>"
        }

        val successBodyType = getParameterUpperBound(0, responseType)
        val errorBodyType = getParameterUpperBound(1, responseType)

        val errorBodyConverter =
            retrofit.nextResponseBodyConverter<Any>(null, errorBodyType, annotations)

        return NetworkResponseApter<Any, Any>(successBodyType, errorBodyConverter)
    }

}