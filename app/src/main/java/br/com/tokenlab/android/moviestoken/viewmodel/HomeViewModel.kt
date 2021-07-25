package br.com.tokenlab.android.moviestoken.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.tokenlab.android.moviestoken.AppConstants
import br.com.tokenlab.android.moviestoken.di.IoDispatcher
import br.com.tokenlab.android.moviestoken.network.NetworkResponse
import br.com.tokenlab.android.moviestoken.network.TokenlabApi
import br.com.tokenlab.android.moviestoken.network.model.dto.MoviesResponseDTOItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import java.lang.Exception
import javax.inject.Inject

val LOGTAG = "logrequest"
// ---------------aqui não tem o parametro private val homeDataSource: HomeDataSouce pois eu so tenho uma requisição
// como criar o view model sem o dataSource ta em 11:00 minutos do video 12 do curso
class HomeViewModel @Inject constructor(val tokenlabApi: TokenlabApi /*, @IoDispatcher private val dispatcher: CoroutineDispatcher*/): ViewModel() {
    private val _listOfMovies: MutableLiveData<List<MoviesResponseDTOItem>>? = MutableLiveData()
    val listOfMovies: LiveData<List<MoviesResponseDTOItem>>? = _listOfMovies

    private val _errorMessage: MutableLiveData<String>? = MutableLiveData()
    val errorMessage: LiveData<String>? = _errorMessage

    private val _errorMessageVisibility: MutableLiveData<Boolean>? = MutableLiveData()
    val errorMessageVisibility: LiveData<Boolean>? = _errorMessageVisibility

    private val _isLoading: MutableLiveData<Boolean>? = MutableLiveData()
    val isLoading: LiveData<Boolean>? = _isLoading

    fun getMoviesList() {
        showErrorMessage(false)
        try {
            viewModelScope.launch() {
                val response = tokenlabApi.getMovies()
                when (response) {
                    is NetworkResponse.Success -> {
                        Log.d(LOGTAG, "Success")
                        _listOfMovies?.value = response.body
                        _isLoading?.value = false
                        _errorMessageVisibility?.value = false
                    }
                    is NetworkResponse.ApiError -> {
                        Log.d(LOGTAG, "ApiError")
                        showErrorMessage(true, AppConstants.API_ERROR_MESSAGE)
                    }
                    is NetworkResponse.NetworkError -> {
                        Log.d(LOGTAG, "NetworkError")
                        showErrorMessage(true, AppConstants.NETWORK_ERROR_MESSAGE)
                    }
                    is NetworkResponse.UnknownError -> {
                        Log.d(LOGTAG, "UnknownError")
                        showErrorMessage(true, AppConstants.UNKNOWN_ERROR_MESSAGE)
                    }
                }
            }
        } catch (e: Exception) {
            throw e
        }
    }
//        viewModelScope.launch {
//            val response = tokenlabApi.getMovies()
//            when(response){
//                is NetworkResponse.Success -> {
//                    Log.d(LOGTAG, "Success")
//                }
//                is NetworkResponse.ApiError -> {
//                    Log.d(LOGTAG, "ApiError")
//                }
//                is NetworkResponse.NetworkError -> {
//                    Log.d(LOGTAG, "NetworkError")
//                }
//                is NetworkResponse.UnknownError -> {
//                    Log.d(LOGTAG, "UnknownError")
//                }
//            }
//        }
//
    private fun showErrorMessage(show: Boolean, message: String? = null){
        _isLoading?.value = !show
        _errorMessageVisibility?.value = show
        _errorMessage?.value = message
    }
}