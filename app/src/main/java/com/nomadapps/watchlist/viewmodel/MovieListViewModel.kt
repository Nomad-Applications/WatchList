package com.nomadapps.watchlist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nomadapps.watchlist.model.MovieModel
import com.nomadapps.watchlist.model.SerieModel

class MovieListViewModel : ViewModel() {
    private var movielistLiveData: MutableLiveData<MovieModel>? = null
    private var moreLikeThisLiveData: MutableLiveData<MovieModel>? = null
    private var similarShowsLiveData: MutableLiveData<SerieModel>? = null
    private var serieListLiveData: MutableLiveData<SerieModel>? = null

    fun getMovieList(): LiveData<MovieModel>? {
        movielistLiveData = MovieListRepository.getMovieListApiCall()
        return movielistLiveData
    }

    fun getMoreLikeThisList(id: String): LiveData<MovieModel>? {
        moreLikeThisLiveData = MovieListRepository.getMoreLikeThisListApiCall(id)

        return moreLikeThisLiveData
    }
    fun getSimilarShows(id: String): LiveData<SerieModel>? {
        similarShowsLiveData = MovieListRepository.getMoreLikeThisSerieListApiCall(id)

        return similarShowsLiveData
    }

    fun getSerieList(): LiveData<SerieModel>? {
        serieListLiveData = MovieListRepository.getSerieListApiCall()
        return serieListLiveData
    }
}