package com.rzaedev.mymovli.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rzaedev.mymovli.databinding.ItemRowMovieBinding
import com.rzaedev.mymovli.model.Movie

class GridMovieAdapter(private val listMovie: ArrayList<Movie>) :
    RecyclerView.Adapter<GridMovieAdapter.GridViewHolder>() {
    class GridViewHolder(var binding: ItemRowMovieBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridViewHolder {
        val view = ItemRowMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GridViewHolder(view)
    }

    override fun getItemCount(): Int = listMovie.size

    override fun onBindViewHolder(holder: GridViewHolder, position: Int) {
        val (title, description, director, starring, censorRating, genre, language, subtitle, duration, posterUrl) = listMovie[position]
        Glide.with(holder.itemView.context).load(posterUrl).fitCenter().into(holder.binding.moviePoster)
        holder.binding.movieTitle.text = title
        holder.binding.movieDescription.text = description
        holder.binding.movieCensorRating.text = censorRating
        holder.binding.movieGenre.text = genre
        holder.binding.movieDuration.text = duration
        holder.itemView.setOnClickListener {
            /* TODO: Handle item click */
        }
    }
}