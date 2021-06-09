package com.nomadapps.watchlist.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "FavoriteMovie")
data class FavoriteMovieEntity(
    @PrimaryKey @ColumnInfo(name = "qwe")val qwe: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "description") val description: String,
)