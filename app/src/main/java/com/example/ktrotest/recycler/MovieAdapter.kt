package com.example.ktrotest.recycler

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ktrotest.R
import com.example.ktrotest.base.BaseDataBindingViewHolder
import com.example.ktrotest.databinding.MovieItemBinding
import com.example.ktrotest.model.DailyBoxOffice

class MovieAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var boxOfficeInfo = mutableListOf<DailyBoxOffice>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(parent,R.layout.movie_item)
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


    inner class MovieViewHolder(parent: ViewGroup,layoutRes: Int):
        BaseDataBindingViewHolder<MovieItemBinding, DailyBoxOffice> (parent,layoutRes){
        override fun bindData(data: DailyBoxOffice) {
            binding.movieInfo = data
        }
    }

}