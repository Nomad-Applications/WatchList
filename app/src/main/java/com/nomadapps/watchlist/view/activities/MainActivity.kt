package com.nomadapps.watchlist.view.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.nomadapps.watchlist.model.MovieModel
import com.nomadapps.watchlist.view.R
import com.nomadapps.watchlist.viewmodel.MovieListViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var movieListViewModel: MovieListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        movieListViewModel = ViewModelProvider(this)[MovieListViewModel::class.java]
        fetchGameList()
    }

    private fun fetchGameList() {
        movieListViewModel.getMovieList()!!.observe(this, Observer<MovieModel> { movielist ->

            Log.e("kaka", movielist.results[0].title)

        })


    }

}
