package com.hino.hearts.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hino.hearts.R
import com.hino.hearts.model.HomeMenu
import com.hino.hearts.ui.home.HomeActivity
import kotlinx.android.synthetic.main.item_add_visit_button.view.*

/**
 * Created by Dihardja Software on 2020-02-18.
 */
class AddVisitButtonAdapter  : RecyclerView.Adapter<AddVisitButtonAdapter.AddVisitButtonHolder>(){

    lateinit var context: Context
    private val mData = ArrayList<HomeMenu>()

    fun setData(items: ArrayList<HomeMenu>) {
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddVisitButtonHolder {
        context = parent.context

        val mView = LayoutInflater.from(parent.context).inflate(R.layout.item_add_visit_button, parent, false)
        return AddVisitButtonHolder(mView)
    }

    override fun onBindViewHolder(holder: AddVisitButtonHolder, position: Int) {
        holder.itemView.setOnClickListener { v: View? ->
            when (mData[position].name) {
                R.string.appointment -> {
                    Toast.makeText(context, "Appointment", Toast.LENGTH_SHORT).show()
                    (context as HomeActivity).hideAddVisitButton()
                }
                R.string.task -> {
                    Toast.makeText(context, "Task", Toast.LENGTH_SHORT).show()
                    (context as HomeActivity).hideAddVisitButton()
                }
                R.string.call_log -> {
                    Toast.makeText(context, "Call Log", Toast.LENGTH_SHORT).show()
                    (context as HomeActivity).hideAddVisitButton()
                }
            }
        }

        holder.bind(mData[position])
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    inner class AddVisitButtonHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(homeMenu: HomeMenu) {
            with(itemView){
                tv_item_add_visit_button.text = context.getString(homeMenu.name)
                Glide.with(context).load(homeMenu.imgRes).into(fab_item_add_visit_button)
            }
        }
    }
}