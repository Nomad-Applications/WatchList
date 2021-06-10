package com.nomadapps.watchlist.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.nomadapps.watchlist.local.FavoriteViewModel
import com.nomadapps.watchlist.model.FavoriteMovieEntity
import com.nomadapps.watchlist.model.MovieDetail
import com.nomadapps.watchlist.view.R
import com.nomadapps.watchlist.viewmodel.MovieDetailViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_acitivity.*
import kotlinx.coroutines.*

class DetailActivity : AppCompatActivity() {
    private lateinit var movieDetailViewModel: MovieDetailViewModel
    private lateinit var favoriteMovieViewModel: FavoriteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_acitivity)
        val id: String? = intent.getStringExtra("key")
        favoriteMovieViewModel = ViewModelProvider(this)[FavoriteViewModel::class.java]
        movieDetailViewModel = ViewModelProvider(this)[MovieDetailViewModel::class.java]
        checkFavoriteGame(id!!.toInt())


        movieDetailViewModel.getMovieDetail(id!!)!!.observe(this, Observer<MovieDetail> { movielist ->
            Picasso.get().load("https://image.tmdb.org/t/p/w500"+movielist.backdrop_path).into(imgItem)
            gameName.text = movielist.title
            releaseDate.text = "Released Date - " + movielist.release_date
            metacriticRate.text = "Rate - " + movielist.vote_average
            gameDescription.text = movielist.overview

            GlobalScope.launch {
                networkCall()
            }

            button_detail_add_favorite.setOnClickListener {
                val temp = FavoriteMovieEntity(
                    movielist.id,
                    movielist.title,
                    movielist.overview,
                    movielist.budget,
                    movielist.release_date,
                    movielist.poster_path,
                    movielist.vote_average,
                    movielist.backdrop_path
                )
                if (button_detail_add_favorite.isSelected) {
                    button_detail_add_favorite.isSelected = false
                    favoriteMovieViewModel.deleteFavoriteMovieDetail(temp)
                    button_detail_add_favorite.setImageResource(R.drawable.ic_notlike)
                    Toast.makeText(this, "Removed to Favorites", Toast.LENGTH_SHORT).show()

                } else {
                    button_detail_add_favorite.isSelected = true
                        favoriteMovieViewModel.insertFavoriteMovieDetail(temp)
                    button_detail_add_favorite.setImageResource(R.drawable.ic_like)
                    Toast.makeText(this, "Added to Favorites", Toast.LENGTH_SHORT).show()

                }
            }
        })

    }
    private suspend fun networkCall() {
        withContext(Dispatchers.Default) {
            delay(1000L)
            runOnUiThread {
                progress_gameDetail.visibility = View.INVISIBLE
                scrollView.visibility = View.VISIBLE
                appBar.visibility = View.VISIBLE
            }
        }
    }

    fun checkFavoriteGame(id: Int) {
        favoriteMovieViewModel.allFavoriteMovieDetail.observe(this, Observer { movielist ->
            for (i in movielist.indices) {
                if (movielist[i].id.equals(id)) {
                    button_detail_add_favorite.setImageResource(R.drawable.ic_like)
                    button_detail_add_favorite.isSelected = true
                }
            }

        })
    }
}