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
import com.hino.hearts.network.response.account.AccountListResponse

class AccountListAdapter(
    var context: Context,
    val document: List<AccountListResponse.AccListData>,
    val listener: OnAdapterTap
) :
    RecyclerView.Adapter<AccountListAdapter.Holder>(){

    interface OnAdapterTap {
        fun onTap(item: AccountListResponse.AccListData)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            LayoutInflater.from(context).inflate(
                R.layout.adapter_account_document,
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

        holder.accountCompanyName.text = doc.account?.accountName

        holder.accountName.text = doc.account?.province

        holder.documentType.text = doc.account?.country

        holder.root.setOnClickListener {
            listener.onTap(doc)
        }
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val accountCompanyName = itemView.findViewById<TextView>(R.id.tv_adapter_approval_acc_company)
        val accountName = itemView.findViewById<TextView>(R.id.tv_adapter_approval_acc_name)
        val documentType = itemView.findViewById<TextView>(R.id.tv_adapter_approval_doc_type)
        val righArrow = itemView.findViewById<ImageView>(R.id.iv_right_arrow)
        val root = itemView.findViewById<ConstraintLayout>(R.id.cl_root)
    }
}