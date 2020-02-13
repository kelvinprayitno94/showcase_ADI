package com.hino.hearts.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hino.hearts.R
import com.hino.hearts.model.HomeMenu
import kotlinx.android.synthetic.main.item_home_menu.view.*
import org.jetbrains.anko.startActivity


/**
 * Created by Dihardja Software on 2020-02-13.
 */
class HomeMenuAdapter : RecyclerView.Adapter<HomeMenuAdapter.HomeViewHolder>(){

    lateinit var context: Context
    private val mData = ArrayList<HomeMenu>()
    var homeMenuInterface: HomeMenuInterface? = null

    interface HomeMenuInterface {
        fun onItemClicked(title: Int)
    }

    fun setData(items: ArrayList<HomeMenu>) {
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        context = parent.context

        val mView = LayoutInflater.from(parent.context).inflate(R.layout.item_home_menu, parent, false)
        return HomeViewHolder(mView)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.itemView.setOnClickListener { v: View? ->
            if (homeMenuInterface != null) {
                homeMenuInterface!!.onItemClicked(mData[position].name)
            }
        }

        holder.bind(mData[position])
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    inner class HomeViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(homeMenu: HomeMenu) {
            with(itemView){
                tv_menu_item.text = context.getString(homeMenu.name)
                Glide.with(context).load(homeMenu.imgRes).into(iv_menu_item)
            }
        }
    }
}
