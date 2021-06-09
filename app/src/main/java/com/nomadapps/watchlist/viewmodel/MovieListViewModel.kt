package com.nomadapps.watchlist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nomadapps.watchlist.model.MovieModel

class MovieListViewModel : ViewModel() {
    private var movielistLiveData: MutableLiveData<MovieModel>? = null

    fun getMovieList(): LiveData<MovieModel>? {
        movielistLiveData = MovieListRepository.getMovieListApiCall()
        return movielistLiveData
    }
}