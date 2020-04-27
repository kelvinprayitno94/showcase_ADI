package com.showcase.movie.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hino.hearts.BuildConfig
import com.hino.hearts.R
import com.hino.hearts.databinding.AdapterGenreBinding
import com.hino.hearts.databinding.AdapterMovieBinding
import com.hino.hearts.databinding.AdapterUserReviewBinding
import com.showcase.movie.network.responses.GenresResponse
import com.showcase.movie.network.responses.MoviesResponse
import com.showcase.movie.network.responses.ReviewResponse
import com.showcase.movie.util.GlideManager

class ReviewAdapter(
    var context: Context,
    var listener: OnAdapterScroll
) :
    RecyclerView.Adapter<ReviewAdapter.Holder>() {

    interface OnAdapterScroll {
        fun isLastPos()
    }

    var itemList: MutableList<ReviewResponse.ReviewModel> = ArrayList()

    fun updateAdapter(list: List<ReviewResponse.ReviewModel>){
        itemList.clear()
        itemList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding: AdapterUserReviewBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.adapter_user_review, parent, false
        )
        return Holder(binding)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {

        holder.bind(context, itemList[position])

        if (position == itemList.size-1)
            listener.isLastPos()

    }

    class Holder(_binding: AdapterUserReviewBinding) : RecyclerView.ViewHolder(_binding.root) {

        val binding = _binding

        fun bind(context: Context, data: ReviewResponse.ReviewModel) {
            binding.data = data
            binding.executePendingBindings()
        }
    }
}