package com.rzaedev.mymovli

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rzaedev.mymovli.adapter.GridMovieAdapter
import com.rzaedev.mymovli.databinding.ActivityMainBinding
import com.rzaedev.mymovli.model.Movie

class MainActivity : AppCompatActivity() {
    private lateinit var rvMovies: RecyclerView
    private var list = ArrayList<Movie>()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        rvMovies = binding.rvMovies
        rvMovies.setHasFixedSize(true)
        list.addAll(getListMovies())
        showRecyclerGrid()
    }

    private fun showRecyclerGrid() {
        rvMovies.layoutManager = GridLayoutManager(this, 2)
        val listMoviesAdapter = GridMovieAdapter(list)
        rvMovies.adapter = listMoviesAdapter

        /* TODO: Handle item click */
    }

    private fun getListMovies(): ArrayList<Movie> {
        val dataTitle = resources.getStringArray(R.array.movie_title)
        val dataDescription = resources.getStringArray(R.array.movie_description)
        val dataDirector = resources.getStringArray(R.array.movie_director)
        val dataStarring = resources.getStringArray(R.array.movie_starring)
        val dataCensorRating = resources.getStringArray(R.array.movie_censor_rating)
        val dataGenre = resources.getStringArray(R.array.movie_genre)
        val dataLanguage = resources.getStringArray(R.array.movie_language)
        val dataSubtitle = resources.getStringArray(R.array.movie_subtitle)
        val dataDuration = resources.getStringArray(R.array.movie_duration)
        val dataPoster = resources.getStringArray(R.array.movie_posters)
        val listMovie = ArrayList<Movie>()

        for (i in dataTitle.indices) {
            val movie = Movie(
                dataTitle[i],
                dataDescription[i],
                dataDirector[i],
                dataStarring[i],
                dataCensorRating[i],
                dataGenre[i],
                dataLanguage[i],
                dataSubtitle[i],
                dataDuration[i],
                dataPoster[i]
            )
            listMovie.add(movie)

        }
        return listMovie
    }

}