package br.com.tokenlab.android.moviestoken.network

import br.com.tokenlab.android.moviestoken.network.model.dto.MoviesResponseDTOItem
import retrofit2.http.GET

//private const val BASE_URL = "https://desafio-mobile.nyc3.digitaloceanspaces.com/"

interface TokenlabApi {

    @GET("movies")
    suspend fun getMovies(
        //@Path("id")
        //username: Double?
    ): NetworkReponse<List<MoviesResponseDTOItem>, ErrorResponse>
}