package br.com.tokenlab.android.moviestoken.network.model.dto

data class MoviesResponseDTOItem(
    val id: Int,
    val vote_average: Double,
    val title: String,
    val poster_url: String,
    val genres: List<String>,
    val release_date: String
)