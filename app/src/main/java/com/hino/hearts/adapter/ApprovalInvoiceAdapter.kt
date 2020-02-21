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
import com.hino.hearts.model.ApprovalDocModel
import com.hino.hearts.model.ApprovalDocumentModel

class ApprovalInvoiceAdapter(var context: Context) :
    RecyclerView.Adapter<ApprovalInvoiceAdapter.Holder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            LayoutInflater.from(context).inflate(
                R.layout.adapter_approval_inv,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return 3
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {

    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}