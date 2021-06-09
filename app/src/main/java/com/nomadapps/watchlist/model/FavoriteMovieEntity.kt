package com.nomadapps.watchlist.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "FavoriteMovie")
data class FavoriteMovieEntity(
    @PrimaryKey @ColumnInfo(name = "ID") val id: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "overview") val overview: String,
    @ColumnInfo(name = "budget") val budget: String,
    @ColumnInfo(name = "release_date") val release_date: String,
    @ColumnInfo(name = "poster_path") val poster_path: String,
    @ColumnInfo(name = "vote_average") val vote_average: Double
)