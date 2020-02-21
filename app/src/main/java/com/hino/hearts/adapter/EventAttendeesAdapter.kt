package com.hino.hearts.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.hino.hearts.R
import com.hino.hearts.model.Attendees
import kotlinx.android.synthetic.main.item_event_attendees.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick

/**
 * Created by Dihardja Software on 2020-02-20.
 */
class EventAttendeesAdapter : RecyclerView.Adapter<EventAttendeesAdapter.EventAttendeesHolder>() {

    lateinit var context: Context
    private val mData = ArrayList<Attendees>()

    fun setData(items: ArrayList<Attendees>) {
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventAttendeesHolder {
        context = parent.context

        val mView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_event_attendees, parent, false)
        return EventAttendeesHolder(mView)
    }

    override fun onBindViewHolder(holder: EventAttendeesHolder, position: Int) {
        val attendee = mData[position]

        holder.itemView.btn_check_in.onClick {
            holder.itemView.btn_check_in.visibility = View.INVISIBLE
            holder.itemView.tv_item_attendees_checked_in.visibility = View.VISIBLE
        }

        holder.bind(mData[position])
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    inner class EventAttendeesHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(attendees: Attendees) {
            with(itemView) {
                tv_item_attendees_account.text = attendees.account
                tv_item_attendees_name.text = attendees.name

                when (attendees.isInvited) {
                    true -> {
                        when (attendees.isCheckedIn) {
                            true -> {
                                btn_check_in.visibility = View.INVISIBLE
                                tv_item_attendees_checked_in.visibility = View.VISIBLE
                            }
                            false -> {
                                btn_check_in.visibility = View.VISIBLE
                                tv_item_attendees_checked_in.visibility = View.INVISIBLE

//                                btn_check_in.onClick {
//                                    Toast.makeText(context, attendees.id, Toast.LENGTH_SHORT).show()
//                                }
                            }
                        }
                    }
                    false -> {
                        btn_check_in.visibility = View.INVISIBLE
                        tv_item_attendees_checked_in.visibility = View.INVISIBLE
                    }
                }


            }
        }
    }
}