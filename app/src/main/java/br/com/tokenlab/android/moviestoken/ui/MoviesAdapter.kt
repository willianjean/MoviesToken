package br.com.tokenlab.android.moviestoken.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.tokenlab.android.moviestoken.R
import br.com.tokenlab.android.moviestoken.network.model.dto.MoviesResponseDTOItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item_movie.view.*
import kotlinx.android.synthetic.main.list_item_rv_movies.view.*


class MoviesAdapter(val context: Context, val movies: List<MoviesResponseDTOItem>)
    : RecyclerView.Adapter<MoviesAdapter.MovieHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item_movie, parent, false)
        return MovieHolder(view)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        movies.elementAt(position).apply {

            //holder.movie.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
//            var lista:MutableList<List<MoviesResponseDTOItem>> = mutableListOf()
//            for (movie in movies)
//                lista.add(listOf(movie))

            //holder.movie.adapter = MoviesAdapter(context, movies)

            Picasso.get()
                .load("${poster_url}")
                .placeholder(R.drawable.ic_broken_image)
                .into(holder.poster)

        }
    }

    class MovieHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var cardView = itemView.cv_movie
        var poster = itemView.iv_movie_poster

        var title = itemView.tv_movies_title
        var movie = itemView.rv_movies
    }




//    class MovieListHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
//        var title = itemView.tv_movies_title
//        var movie = itemView.rv_movies
//    }
}