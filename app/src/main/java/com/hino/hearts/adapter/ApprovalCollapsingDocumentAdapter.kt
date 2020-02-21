package com.hino.hearts.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.hino.hearts.R
import com.hino.hearts.model.ApprovalDocModel
import com.hino.hearts.model.ApprovalDocumentModel
import com.hino.hearts.network.response.approve.ApprovalListResponse
import org.jetbrains.anko.textColor

class ApprovalCollapsingDocumentAdapter(
    var context: Context,
    val document: List<ApprovalListResponse.ApprovalListData>,
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
        return document.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val doc = document[position]

        holder.documentType.text = doc.type

        holder.root.setOnClickListener {
            listener.onTap(position)
        }

        if (position == selected){
            holder.documentType.textColor = context.resources.getColor(R.color.red)
            holder.redIndicator.visibility = View.VISIBLE
        }else{
            holder.documentType.textColor = context.resources.getColor(R.color.black)
            holder.redIndicator.visibility = View.INVISIBLE
        }
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val documentType = itemView.findViewById<TextView>(R.id.tv_adapter_approval_collapsing_acc_name)
        val redIndicator = itemView.findViewById<View>(R.id.view_bot_indicator)
        val root = itemView.findViewById<ConstraintLayout>(R.id.cl_root)
    }
}