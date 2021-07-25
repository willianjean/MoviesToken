//package br.com.tokenlab.android.moviestoken.repository
//
//import android.content.Context
//import br.com.tokenlab.android.moviestoken.network.NetworkResponse
//import br.com.tokenlab.android.moviestoken.network.TokenlabApi
//import br.com.tokenlab.android.moviestoken.network.model.dto.MoviesResponseDTOItem
//import kotlinx.coroutines.CoroutineDispatcher
//import kotlinx.coroutines.async
//import kotlinx.coroutines.withContext
//import java.io.IOException
//import javax.inject.Inject
//
//class HomeDataSourceImpl
//    @Inject
//    constructor(val context: Context, private val tokenlabApi: TokenlabApi)
//    : HomeDataSource {
//
//    override suspend fun getListsOfMovies(
//        dispatcher: CoroutineDispatcher,
//        homeResultCallback:  (result: NetworkResponse<List<List<List<MoviesResponseDTOItem>>>, Error>) -> Unit) {
//        withContext(dispatcher){
//            // This try catch is usefull for notifying us about any thread issue like ths use of .value instead of .postValue in a background thread
//            try {
//                val getMoviesResponse = async { tokenlabApi.getMovies() }
//                processData(
//                    homeResultCallback,
//                    getMoviesResponse.await()
//                )
//            } catch (exception: Exception) {
//                throw  exception
//            }
//        }
//    }
//
////    override fun isTopMovieInDatabase(id: Int): Boolean {
////        return myListDao.exists(id) ?: false
////    }
////
////    override fun refresh() {
////        //        fetchMoviesLists()
////    }
////
////    override fun insert(myListItem: MyListItem) {
////        myListDao.insert(myListItem)
////    }
////
////    override fun delete(id: Int) {
////        myListDao.deleteById(id)
////    }
////
//    private fun processData(
//    homeResultCallback: (result: NetworkResponse<List<List<List<MoviesResponseDTOItem>>>, Error>) -> Unit,
//                            listOfMovies: NetworkResponse<List<MoviesResponseDTOItem>, Error>,
//    ) {
//        val pair1 = buildResponse(listOfMovies)
//
//        when {
//            pair1.first == null -> {
//                pair1.second?.let { homeResultCallback(it) }
//                return
//            }
////            pair2.first == null -> {
////                pair2.second?.let { homeResultCallback(it) }
////                return
////            }
////            pair2.first == null -> {
////                pair2.second?.let { homeResultCallback(it) }
////                return
////            }
////            pair2.first == null -> {
////                pair2.second?.let { homeResultCallback(it) }
////                return
////            }
//            else -> {
//                val resultList = ArrayList<List<List<MoviesResponseDTOItem>>>()
//                pair1.first?.let { resultList.add(it) }
//                homeResultCallback(NetworkResponse.Success(resultList))
//            }
//        }
//    }
//
//    private fun buildResponse(response: NetworkResponse<List<MoviesResponseDTOItem>, Error>)
//            : Pair<List<List<MoviesResponseDTOItem>>?, NetworkResponse<List<List<List<MoviesResponseDTOItem>>>, Error>?>
//    {
//        return when(response) {
////            is NetworkResponse.Success -> {
////                Pair(response.body.results, null)
////            }
//            is NetworkResponse.ApiError -> {
//                Pair(null, NetworkResponse.ApiError(response.body, response.code))
//            }
//            is NetworkResponse.NetworkError -> {
//                Pair(null, NetworkResponse.NetworkError(IOException()))
//            }
//            is NetworkResponse.UnknownError -> {
//                Pair(null, NetworkResponse.UnknownError(Throwable()))
//            }
//        }
//    }
//}
