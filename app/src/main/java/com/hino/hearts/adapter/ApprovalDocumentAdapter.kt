package com.hino.hearts.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.hino.hearts.R
import com.hino.hearts.network.response.approve.ApprovalListResponse

class ApprovalDocumentAdapter(
    var context: Context,
    val document: List<ApprovalListResponse.ApprovalListData>,
    val roleID: Int,
    val listener: OnAdapterTap
) :
    RecyclerView.Adapter<ApprovalDocumentAdapter.Holder>() {

    interface OnAdapterTap {
        fun onTap(pos: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            LayoutInflater.from(context).inflate(
                R.layout.adapter_approval_document,
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

        holder.accountName.text = doc.approval?.customerName

        holder.documentType.text = doc.type

        if (roleID > 1) {
            holder.greenDot.visibility = View.VISIBLE
        } else
            holder.greenDot.visibility = View.INVISIBLE

        holder.root.setOnClickListener {
            listener.onTap(position)
        }
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val accountName = itemView.findViewById<TextView>(R.id.tv_adapter_approval_acc_name)
        val documentType = itemView.findViewById<TextView>(R.id.tv_adapter_approval_doc_type)
        val greenDot = itemView.findViewById<ImageView>(R.id.ic_green_dot)
        val root = itemView.findViewById<ConstraintLayout>(R.id.cl_root)
    }
}