package com.nomadapps.watchlist.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nomadapps.watchlist.model.MovieModel
import com.nomadapps.watchlist.model.Result
import com.nomadapps.watchlist.model.SerieModel
import com.nomadapps.watchlist.model.SerieResult
import com.nomadapps.watchlist.view.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.custom_row_design_more.view.*

class SeriesSimilarShowAdapter(
    private val items: SerieModel,
    private val onClickListenerCategory: OnClickListener
) : RecyclerView.Adapter<SeriesSimilarShowAdapter.ViewHolder>() {

    interface OnClickListener {
        fun onItemClick(position: SerieResult)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.custom_row_design_more, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.results.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            onClickListenerCategory.onItemClick(items.results[position])
        }
        val currentItem = items.results[position]
        Picasso.get().load("https://image.tmdb.org/t/p/w500" + currentItem.poster_path)
            .into(holder.itemView.moreImageView)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }
}