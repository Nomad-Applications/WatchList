package com.nomadapps.watchlist.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.nomadapps.watchlist.api.ApiClient
import com.nomadapps.watchlist.model.MovieModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object MovieListRepository {
    private const val API_KEY = "76d1caf3e6b83eaaa662ea3bb10873ed"
    val movieList = MutableLiveData<MovieModel>()

    fun getMovieListApiCall(): MutableLiveData<MovieModel> {
        val call = ApiClient.apiInterface.getMovieList(API_KEY,1)

        call.enqueue(object : Callback<MovieModel> {
            override fun onFailure(call: Call<MovieModel>, t: Throwable) {
                Log.e("DEBUG:", t.message.toString())
            }

            override fun onResponse(
                call: Call<MovieModel>,
                response: Response<MovieModel>
            ) {
                Log.e("rere", response.toString())
                Log.e("DEBUG:" , response.body().toString())
                movieList.value = response.body()

            }
        })

        return movieList
    }
}