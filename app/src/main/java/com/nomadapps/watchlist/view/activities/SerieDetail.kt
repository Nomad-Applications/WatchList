package com.nomadapps.watchlist.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.nomadapps.watchlist.local.FavoriteViewModel
import com.nomadapps.watchlist.model.*
import com.nomadapps.watchlist.view.R
import com.nomadapps.watchlist.view.adapters.SeriesSimilarShowAdapter
import com.nomadapps.watchlist.viewmodel.MovieListViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_serie_detail.*
import kotlinx.coroutines.*

class SerieDetail : AppCompatActivity() {

    private lateinit var favoriteMovieViewModel: FavoriteViewModel
    private lateinit var serieListViewModel: MovieListViewModel
    private var adapterMoreRecyclerView: SeriesSimilarShowAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_serie_detail)
        val serieResult: SerieResult? = intent.getParcelableExtra<SerieResult>("key")

        favoriteMovieViewModel = ViewModelProvider(this)[FavoriteViewModel::class.java]
        serieListViewModel = ViewModelProvider(this)[MovieListViewModel::class.java]

        checkFavoriteGame(serieResult!!.id)

        Picasso.get().load("https://image.tmdb.org/t/p/w500" + serieResult.backdrop_path)
            .into(imgItem)
        gameName.text = serieResult.name
        releaseDate.text = "Released Date - " + serieResult.first_air_date
        metacriticRate.text = "Rate - " + serieResult.vote_average
        gameDescription.text = serieResult.overview

        GlobalScope.launch {
            networkCall()
        }

        button_detail_add_favorite.setOnClickListener {
            val temp = FavoriteMovieEntity(
                serieResult.id,
                "serie",
                serieResult.name,
                serieResult.overview,
                serieResult.first_air_date,
                serieResult.poster_path,
                serieResult.vote_average,
                serieResult.backdrop_path,

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


        serieListViewModel.getSimilarShows(serieResult.id.toString())?.observe(this, { it ->

            adapterMoreRecyclerView = SeriesSimilarShowAdapter(
                it,
                object : SeriesSimilarShowAdapter.OnClickListener {
                    override fun onItemClick(position: SerieResult) {
                        val intent = Intent(this@SerieDetail, SerieDetail::class.java)
                        intent.putExtra("key", position)
                        startActivity(intent)

                    }

                })

            moreLikeThisRecycleView.isNestedScrollingEnabled = false

            moreLikeThisRecycleView.adapter = adapterMoreRecyclerView
            moreLikeThisRecycleView.layoutManager = GridLayoutManager(this@SerieDetail, 4)

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
        favoriteMovieViewModel.allFavoriteMovieDetail.observe(this, Observer { serielist ->
            for (i in serielist.indices) {
                if (serielist[i].id.equals(id)) {
                    button_detail_add_favorite.setImageResource(R.drawable.ic_like)
                    button_detail_add_favorite.isSelected = true
                }
            }

        })
    }
    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this@SerieDetail, SerieActivity::class.java)
        startActivity(intent)
        overridePendingTransition(0, 0)
    }
}