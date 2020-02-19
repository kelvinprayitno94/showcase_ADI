package com.hino.hearts.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.hino.hearts.R
import com.hino.hearts.model.AccountListModel
import com.hino.hearts.model.ApprovalDocModel
import com.hino.hearts.model.ApprovalDocumentModel
import java.util.*
import kotlin.collections.ArrayList

class AccountListAdapter(
    var context: Context,
    val document: MutableList<AccountListModel>,
    val listener: OnAdapterTap
) :
    RecyclerView.Adapter<AccountListAdapter.Holder>(){

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

        holder.accountName.text = doc.companyName

        holder.documentType.text = doc.address

        holder.righArrow.rotation = 90f

        holder.root.setOnClickListener {
            listener.onTap(position)
        }
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val accountName = itemView.findViewById<TextView>(R.id.tv_adapter_approval_acc_name)
        val documentType = itemView.findViewById<TextView>(R.id.tv_adapter_approval_doc_type)
        val righArrow = itemView.findViewById<ImageView>(R.id.iv_right_arrow)
        val root = itemView.findViewById<ConstraintLayout>(R.id.cl_root)
    }
}