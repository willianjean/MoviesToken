package br.com.tokenlab.android.moviestoken.network

import br.com.tokenlab.android.moviestoken.network.model.dto.MoviesResponseDTO
import br.com.tokenlab.android.moviestoken.network.model.dto.MoviesResponseDTOItem
import retrofit2.http.GET
import retrofit2.http.Path

//private const val BASE_URL = "https://desafio-mobile.nyc3.digitaloceanspaces.com/"

interface TokenlabApi {

    @GET("movies")
    suspend fun getMovies(
        //@Path("id")
        //id: Double?
    ): NetworkResponse<List<MoviesResponseDTOItem>, ErrorResponse>
}