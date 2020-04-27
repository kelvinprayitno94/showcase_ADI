package com.showcase.movie.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.hino.hearts.R
import com.hino.hearts.databinding.AdapterGenreBinding
import com.showcase.movie.network.responses.GenresResponse

class GenresAdapter(
    var context: Context,
    var itemList: List<GenresResponse.GenresModel>,
    val listener: OnAdapterTap
) :
    RecyclerView.Adapter<GenresAdapter.Holder>() {

    interface OnAdapterTap {
        fun onTap(data: GenresResponse.GenresModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding: AdapterGenreBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.adapter_genre, parent, false
        )
        return Holder(binding)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {

        holder.bind(itemList[position], listener)
    }

    class Holder(_binding: AdapterGenreBinding) : RecyclerView.ViewHolder(_binding.root) {

        val binding = _binding

        fun bind(data: GenresResponse.GenresModel, listener: OnAdapterTap) {
            binding.data = data
            binding.action = listener
            binding.executePendingBindings()
        }
    }
}