package com.hino.hearts.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.hino.hearts.R
import com.hino.hearts.model.Notification
import com.hino.hearts.ui.notification.NotificationDetailActivity
import kotlinx.android.synthetic.main.item_notification.view.*
import org.jetbrains.anko.startActivity

/**
 * Created by Dihardja Software on 2020-02-17.
 */
class NotificationAdapter : RecyclerView.Adapter<NotificationAdapter.NotificationHolder>(){

    lateinit var context: Context
    private val mData = ArrayList<Notification>()

    fun setData(items: ArrayList<Notification>) {
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationHolder {
        context = parent.context

        val mView = LayoutInflater.from(parent.context).inflate(R.layout.item_notification, parent, false)
        return NotificationHolder(mView)
    }

    override fun onBindViewHolder(holder: NotificationHolder, position: Int) {
        holder.itemView.setOnClickListener { v: View? ->
            context.startActivity<NotificationDetailActivity>()
        }

        holder.bind(mData[position])
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    inner class NotificationHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(notification: Notification) {
            with(itemView){
                tv_notification_item_title.text = notification.title
                tv_notification_item_time.text = notification.date
            }
        }
    }
}