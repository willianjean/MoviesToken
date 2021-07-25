//package br.com.tokenlab.android.moviestoken.repository
//
//import br.com.tokenlab.android.moviestoken.network.NetworkResponse
//import br.com.tokenlab.android.moviestoken.network.model.dto.MoviesResponseDTOItem
//import kotlinx.coroutines.CoroutineDispatcher
//
//interface HomeDataSource {
//    suspend fun getListsOfMovies(dispatcher: CoroutineDispatcher, homeResultCallback: (result: NetworkResponse<List<List<List<MoviesResponseDTOItem>>>, Error>) -> Unit)
//
//    //fun isTopMovieInDatabase(id: Int) : Boolean
//
//    fun refresh()
//
//    fun insert(moviesResponseDTOItem: MoviesResponseDTOItem)
//
//    fun delete(id: Int)
//}