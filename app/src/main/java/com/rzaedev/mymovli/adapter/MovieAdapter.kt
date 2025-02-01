package com.rzaedev.mymovli.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rzaedev.mymovli.databinding.ItemRowMovieBinding
import com.rzaedev.mymovli.model.MovieModel

class MovieAdapter(
    private val listMovieModel: ArrayList<MovieModel>,
    private val onItemCLick: (MovieModel) -> Unit
) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    inner class MovieViewHolder(private var binding: ItemRowMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieModel) {
            binding.movieTitle.text = movie.title
            binding.movieDescription.text = movie.description
            binding.movieCensorRating.text = movie.censorRating
            binding.movieGenre.text = movie.genre
            Glide.with(itemView.context).load(movie.posterUrl).fitCenter().into(binding.moviePoster)
            itemView.setOnClickListener {
                onItemCLick(movie)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = ItemRowMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int = listMovieModel.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movies = listMovieModel[position]
        holder.bind(movies)
    }
}