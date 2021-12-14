package com.example.ktrotest.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ktrotest.databinding.MovieItemBinding
import com.example.ktrotest.model.DailyBoxOffice
import com.example.ktrotest.model.OfficeResult

class MovieAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var boxOfficeInfo = mutableListOf<DailyBoxOffice>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val movieItemView = MovieItemBinding.inflate(inflater,parent,false)
        return MovieViewHolder(movieItemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data =boxOfficeInfo[position]
        (holder as MovieViewHolder).bind(data)
    }

    override fun getItemCount(): Int {
        return boxOfficeInfo.size
    }

    fun setData(data : ArrayList<DailyBoxOffice>){
        boxOfficeInfo = data
        notifyDataSetChanged()
    }

}