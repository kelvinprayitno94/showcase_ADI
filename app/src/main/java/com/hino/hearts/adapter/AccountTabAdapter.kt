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
import org.jetbrains.anko.textColor

class AccountTabAdapter(
    var context: Context,
    val document: ApprovalDocModel,
    val listener: OnAdapterTap
) :
    RecyclerView.Adapter<AccountTabAdapter.Holder>() {

    interface OnAdapterTap {
        fun onTap(pos: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            LayoutInflater.from(context).inflate(
                R.layout.adapter_approval_doc_type_tab,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return document.docList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val doc = document.docList[position]

        holder.documentType.text = doc.accountName

        holder.root.setOnClickListener {
            listener.onTap(position)
        }

        if (document.prevSelected == position){
            holder.documentType.textColor = ContextCompat.getColor(context, R.color.red)
            holder.redIndicator.visibility = View.VISIBLE
        }else{
            holder.documentType.textColor = ContextCompat.getColor(context, R.color.black)
            holder.redIndicator.visibility = View.INVISIBLE
        }
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val documentType = itemView.findViewById<TextView>(R.id.tv_adapter_approval_doc_type)
        val redIndicator = itemView.findViewById<View>(R.id.view_bot_indicator)
        val root = itemView.findViewById<ConstraintLayout>(R.id.cl_root)
    }
}