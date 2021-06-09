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
        Log.e("rere","response.toString()")
        val call = ApiClient.apiInterface.getMovieList(API_KEY)

        call.enqueue(object : Callback<MovieModel> {
            override fun onFailure(call: Call<MovieModel>, t: Throwable) {
                Log.v("DEBUG : ", t.message.toString())
            }

            override fun onResponse(
                call: Call<MovieModel>,
                response: Response<MovieModel>
            ) {
                Log.e("rere",response.toString())
                Log.v("DEBUG : ", response.body().toString())
                movieList.value = response.body()

            }
        })

        return movieList
    }
}