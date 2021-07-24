package br.com.tokenlab.android.moviestoken.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.tokenlab.android.moviestoken.network.NetworkReponse
import br.com.tokenlab.android.moviestoken.network.TokenlabApi
import br.com.tokenlab.android.moviestoken.network.model.dto.MoviesResponseDTO
import br.com.tokenlab.android.moviestoken.network.model.dto.MoviesResponseDTOItem
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

val LOGTAG = "logrequest"

class HomeViewModel @Inject constructor(val tokenlabApi: TokenlabApi): ViewModel() {
    fun getMoviesList(){
        viewModelScope.launch {
            val response = tokenlabApi.getMovies()
            when(response){
                is NetworkReponse.Success -> {
                    Log.d(LOGTAG, "Success")
                }
                is NetworkReponse.ApiError -> {
                    Log.d(LOGTAG, "ApiError")
                }
                is NetworkReponse.NetworkError -> {
                    Log.d(LOGTAG, "NetworkError")
                }
                is NetworkReponse.UnknownError -> {
                    Log.d(LOGTAG, "UnknownError")
                }
            }
        }

    }
}