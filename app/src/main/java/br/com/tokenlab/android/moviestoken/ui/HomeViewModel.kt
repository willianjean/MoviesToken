package br.com.tokenlab.android.moviestoken.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import br.com.tokenlab.android.moviestoken.network.TokenlabApi
import br.com.tokenlab.android.moviestoken.network.model.dto.MoviesResponseDTO
import br.com.tokenlab.android.moviestoken.network.model.dto.MoviesResponseDTOItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class HomeViewModel @Inject constructor(val tokenlabApi: TokenlabApi): ViewModel() {
    fun getMoviesList(){
        tokenlabApi.getMovies().enqueue(object : Callback<List<MoviesResponseDTOItem>>{
            override fun onResponse(
                call: Call<List<MoviesResponseDTOItem>>,
                response: Response<List<MoviesResponseDTOItem>>
            ) {
                if(response.isSuccessful){

                }
            }

            override fun onFailure(call: Call<List<MoviesResponseDTOItem>>, t: Throwable) {
                Log.d("meuteste", "onFailure")
            }

        })
    }
}