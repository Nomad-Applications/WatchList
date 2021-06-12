package com.nomadapps.watchlist.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class SerieModel(
    val results: List<SerieResult>
)
@Parcelize
data class SerieResult(
    val id: Int,
    val name: String,
    val first_air_date: String,
    val poster_path: String,
    val vote_average: Double,
    val backdrop_path: String,
    val overview:String
) : Parcelable
