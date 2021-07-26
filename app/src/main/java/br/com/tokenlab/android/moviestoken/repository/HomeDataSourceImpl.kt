package br.com.tokenlab.android.moviestoken.repository

import br.com.tokenlab.android.moviestoken.network.ErrorResponse
import br.com.tokenlab.android.moviestoken.network.NetworkResponse
import br.com.tokenlab.android.moviestoken.network.TokenlabApi
import br.com.tokenlab.android.moviestoken.network.model.dto.MoviesResponseDTOItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject


class HomeDataSourceImpl @Inject constructor(private val tokenlabApi: TokenlabApi) : HomeDataSource{
    override suspend fun getListsOfMovies(
        dispatcher: CoroutineDispatcher,
        homeResultCallback: (result: NetworkResponse<List<List<List<MoviesResponseDTOItem>>>, ErrorResponse>) -> Unit
    ) {
//        withContext(dispatcher){
//            try {
//                val moviesResponseHDS = async { tokenlabApi.getMovies() }
//
//                processData(
//                    homeResultCallback,
//                    moviesResponseHDS.await())
//            } catch (e: Exception){
//                throw e
//            }
//
//        }
    }

//    private fun processData(
//        homeResultCallback: (result: NetworkResponse<List<List<List<MoviesResponseDTOItem>>>, ErrorResponse>) -> Unit,
//        moviesL: NetworkResponse<List<MoviesResponseDTOItem>, ErrorResponse>): Nothing {
//        val pair = buildResponse(moviesL)
//
//    }

//    private fun buildResponse(response: NetworkResponse<List<MoviesResponseDTOItem>, ErrorResponse>)
//            : Pair<List<List<MoviesResponseDTOItem>>,
//            NetworkResponse<List<List<List<MoviesResponseDTOItem>>>, ErrorResponse>> {
//        return when(response){
//            is NetworkResponse.Success -> {
//                Pair(response.body)
//            }
//        }
//    }

}
