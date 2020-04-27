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
import com.showcase.movie.network.responses.GenresResponse
import com.showcase.movie.network.responses.MoviesResponse
import com.showcase.movie.util.GlideManager

class MoviesAdapter(
    var context: Context,
    val listener: OnAdapterTap
) :
    RecyclerView.Adapter<MoviesAdapter.Holder>() {

    interface OnAdapterTap {
        fun onTap(data: MoviesResponse.MoviesModel)
        fun isLastPos()
    }

    var itemList: MutableList<MoviesResponse.MoviesModel> = ArrayList()

    fun updateAdapter(list: List<MoviesResponse.MoviesModel>){
        itemList.clear()
        itemList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding: AdapterMovieBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.adapter_movie, parent, false
        )
        return Holder(binding)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {

        holder.bind(context, itemList[position], listener)

        if (position == itemList.size-1)
            listener.isLastPos()
    }

    class Holder(_binding: AdapterMovieBinding) : RecyclerView.ViewHolder(_binding.root) {

        val binding = _binding

        fun bind(context: Context, data: MoviesResponse.MoviesModel, listener: OnAdapterTap) {
            binding.data = data
            binding.action = listener
            GlideManager.getInstance().LoadImageCenterCrop(context, BuildConfig.IMG_URL+data.poster_path, binding.image)
            binding.executePendingBindings()
        }
    }
}