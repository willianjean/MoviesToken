package br.com.tokenlab.android.moviestoken.di

import br.com.tokenlab.android.moviestoken.AppConstants
import br.com.tokenlab.android.moviestoken.network.TokenlabApi
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class  NetworkModule{
    @Singleton
    @Provides
    fun providesInterceptor(): Interceptor{
        return Interceptor { chain ->  
            val newUrl = chain.request().url
                .newBuilder()
                .addQueryParameter("api_key", AppConstants.TMDB_API_KEY)
                .build()
            val newRequest = chain.request()
                .newBuilder()
                .url(newUrl)
                .build()

            chain.proceed(newRequest)
        }
    }

    @Singleton
    @Provides
    fun loggingClient(authInterceptor: Interceptor): OkHttpClient{
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .readTimeout(15,TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .addNetworkInterceptor(interceptor)
            .addNetworkInterceptor(authInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun providesRetrofitInstance():Retrofit{
        return Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            //.client(loggingClient) //somente se precisar de autenticação
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun tokenlabApi(retrofit: Retrofit): TokenlabApi{
        return retrofit.create(TokenlabApi::class.java)
    }

}