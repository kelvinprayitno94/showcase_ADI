package com.hino.hearts.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hino.hearts.BuildConfig
import com.hino.hearts.R
import com.hino.hearts.model.Event
import com.hino.hearts.ui.event.EventDetailActivity
import com.hino.hearts.util.ConstantManager
import kotlinx.android.synthetic.main.item_event.view.*
import org.jetbrains.anko.startActivity

/**
 * Created by Dihardja Software on 2020-02-19.
 */
class EventAdapter : RecyclerView.Adapter<EventAdapter.EventHolder>(){

    lateinit var context: Context
    private val mData = ArrayList<Event>()

    fun setData(items: ArrayList<Event>) {
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventHolder {
        context = parent.context

        val mView = LayoutInflater.from(parent.context).inflate(R.layout.item_event, parent, false)
        return EventHolder(mView)
    }

    override fun onBindViewHolder(holder: EventHolder, position: Int) {
        val event = mData[position]

        holder.itemView.setOnClickListener {
            context.startActivity<EventDetailActivity>(ConstantManager.EVENT_ID to event.id)
        }

        holder.bind(mData[position])
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    inner class EventHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(event: Event) {
            with(itemView){
                tv_item_event_title.text = event.title
                tv_item_event_desc.text = event.date
                Glide.with(context).load(BuildConfig.IMAGE_URL+event.imgUrl).placeholder(R.drawable.header).into(iv_item_event)
            }
        }
    }
}