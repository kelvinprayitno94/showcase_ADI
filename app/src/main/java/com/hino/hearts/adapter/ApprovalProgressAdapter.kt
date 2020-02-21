package com.hino.hearts.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.hino.hearts.R

class ApprovalProgressAdapter(var context: Context) :
    RecyclerView.Adapter<ApprovalProgressAdapter.Holder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            LayoutInflater.from(context).inflate(
                R.layout.adapter_approval_progress,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return 4
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        if (position == 0) {
            holder.upperView.visibility = View.GONE
        } else if (position == 3) {
            holder.lowerView.visibility = View.GONE
        } else {
            holder.upperView.visibility = View.VISIBLE
            holder.lowerView.visibility = View.VISIBLE
        }

        if (position == 3 || position == 4) {
            holder.status.setImageResource(R.drawable.ic_bullet)
        } else
            holder.status.setImageResource(R.drawable.ic_checked)
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val upperView = itemView.findViewById<View>(R.id.view_app_prog_upper)
        val lowerView = itemView.findViewById<View>(R.id.view_app_prog_lower)
        val status = itemView.findViewById<ImageView>(R.id.iv_status)
    }
}