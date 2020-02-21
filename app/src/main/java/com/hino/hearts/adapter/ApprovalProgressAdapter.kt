package com.hino.hearts.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hino.hearts.R
import com.hino.hearts.network.response.approve.ApprovalListResponse
import com.hino.hearts.util.InterfaceManager

class ApprovalProgressAdapter(var context: Context, var data: ApprovalListResponse.ApprovalListData) :
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
        return data.approvalProgress?.size!!
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        if (position == 0) {
            holder.upperView.visibility = View.GONE
        } else if (position == data.approvalProgress?.size!!-1) {
            holder.lowerView.visibility = View.GONE
        } else {
            holder.upperView.visibility = View.VISIBLE
            holder.lowerView.visibility = View.VISIBLE
        }

        var item = data.approvalProgress?.get(position)
        item?.run {
            if (approved!!) {
                holder.status.setImageResource(R.drawable.ic_checked_round)
            } else
                holder.status.setImageResource(R.drawable.ic_bullet)

            holder.approvalDept.text = name
            holder.approvalDate.text = InterfaceManager.getInstance().convertStringFromDate(
                InterfaceManager.getInstance().convertDateFromString(date))
        }
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val upperView = itemView.findViewById<View>(R.id.view_app_prog_upper)
        val lowerView = itemView.findViewById<View>(R.id.view_app_prog_lower)
        val status = itemView.findViewById<ImageView>(R.id.iv_status)
        val approvalDept = itemView.findViewById<TextView>(R.id.tv_approval_dept)
        val approvalDate = itemView.findViewById<TextView>(R.id.tv_approval_date)
    }
}