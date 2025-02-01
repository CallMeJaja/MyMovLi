package com.rzaedev.mymovli.ui

import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.rzaedev.mymovli.R
import com.rzaedev.mymovli.databinding.ActivityMovieDetailBinding
import com.rzaedev.mymovli.model.MovieModel

class MovieDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true) // Back Button
            setDisplayShowHomeEnabled(true) // Home Button
        }

        val movie: MovieModel? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("MOVIE_DATA", MovieModel::class.java)
        } else {
            intent.getParcelableExtra("MOVIE_DATA")
        }

        movie?.let {
            binding.movieTitle.text = it.title
            binding.movieDescription.text = it.description
            binding.movieCensorRating.text = it.censorRating
            binding.movieGenre.text = it.genre
            binding.movieDuration.text = it.duration
            binding.movieLanguage.text = it.language
            binding.movieSubtitle.text = it.subtitle
            binding.movieDirector.text = it.director
            binding.movieStarring.text = it.starring
            Glide.with(this).load(it.posterUrl).fitCenter().into(binding.moviePoster)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == android.R.id.home) {
            onBackPressedDispatcher.onBackPressed()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }
}