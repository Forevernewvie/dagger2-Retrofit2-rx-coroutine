package com.example.ktrotest.recycler

import androidx.recyclerview.widget.RecyclerView
import com.example.ktrotest.databinding.MovieItemBinding
import com.example.ktrotest.model.DailyBoxOffice

class MovieViewHolder(private val movieInfoItem: MovieItemBinding) : RecyclerView.ViewHolder(movieInfoItem.root) {

    fun bind(data: DailyBoxOffice){
        movieInfoItem.movieInfo = data
    }

}