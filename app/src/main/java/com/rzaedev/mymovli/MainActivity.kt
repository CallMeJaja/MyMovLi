package com.rzaedev.mymovli

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rzaedev.mymovli.adapter.MovieAdapter
import com.rzaedev.mymovli.databinding.ActivityMainBinding
import com.rzaedev.mymovli.model.MovieModel
import com.rzaedev.mymovli.ui.MovieDetailActivity
import com.rzaedev.mymovli.ui.ProfileActivity

class MainActivity : AppCompatActivity() {
    private lateinit var rvMovies: RecyclerView
    private var list = ArrayList<MovieModel>()

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.about_id -> {
                val intent = Intent(this@MainActivity, ProfileActivity::class.java)
                startActivity(intent)
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun showRecyclerGrid() {
        rvMovies.layoutManager = GridLayoutManager(this, 2)
        val listMoviesAdapter = MovieAdapter(list) { movie ->
            val intent = Intent(this@MainActivity, MovieDetailActivity::class.java)
            intent.putExtra("MOVIE_DATA", movie)
            startActivity(intent)
        }
        rvMovies.adapter = listMoviesAdapter
    }

    private fun getListMovies(): ArrayList<MovieModel> {
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
        val listMovieModel = ArrayList<MovieModel>()

        for (i in dataTitle.indices) {
            val movieModel = MovieModel(
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
            listMovieModel.add(movieModel)
        }
        return listMovieModel
    }

}