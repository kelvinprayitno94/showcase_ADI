package com.hino.hearts.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.hino.hearts.R
import com.hino.hearts.model.ApprovalDocModel
import com.hino.hearts.model.ApprovalDocumentModel
import com.hino.hearts.network.response.approve.ApprovalListResponse
import org.jetbrains.anko.textColor

class ApprovalCollapsingDocumentAdapter(
    var context: Context,
    val document: ApprovalListResponse,
    val tabList: List<String>,
    var selected: Int,
    val listener: OnAdapterTap
) :
    RecyclerView.Adapter<ApprovalCollapsingDocumentAdapter.Holder>() {

    interface OnAdapterTap {
        fun onTap(pos: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            LayoutInflater.from(context).inflate(
                R.layout.adapter_approval_collapsing_document,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return tabList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
//        val doc = document.listData[position]

        holder.documentType.text = tabList[position]

        holder.root.setOnClickListener {
            listener.onTap(position)
        }

        if (position == document.selected){
            holder.documentType.textColor = ContextCompat.getColor(context, R.color.red)
            holder.redIndicator.visibility = View.VISIBLE
        }else{
            holder.documentType.textColor = ContextCompat.getColor(context, R.color.black)
            holder.redIndicator.visibility = View.INVISIBLE
        }
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val documentType = itemView.findViewById<TextView>(R.id.tv_adapter_approval_collapsing_acc_name)
        val redIndicator = itemView.findViewById<View>(R.id.view_bot_indicator)
        val root = itemView.findViewById<ConstraintLayout>(R.id.cl_root)
    }
}