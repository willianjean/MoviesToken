package br.com.tokenlab.android.moviestoken.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.tokenlab.android.moviestoken.R
import br.com.tokenlab.android.moviestoken.network.model.dto.MoviesResponseDTOItem
import kotlinx.android.synthetic.main.list_item_rv_movies.view.*


//val lists: List<List<MoviesResponseDTOItem>> como parametro de ListOfMoviesAdapter
class ListOfMoviesAdapter(val context: Context, val lists: List<List<MoviesResponseDTOItem>>)
    : RecyclerView.Adapter<ListOfMoviesAdapter.MovieListHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item_rv_movies, parent, false)
        return MovieListHolder(view)
    }

    override fun onBindViewHolder(holder: MovieListHolder, position: Int) {
        holder.title.text = when(position){
            0 -> "Movies List"
            else -> "Movies"
        }
        holder.movies.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        holder.movies.adapter = MoviesAdapter(context, lists[position])
        //lists[position] como parametro de MoviesAdapter
    }

    override fun getItemCount(): Int {
        return lists.size
    }

    class MovieListHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var title = itemView.tv_movies_title
        var movies = itemView.rv_movies
    }
}