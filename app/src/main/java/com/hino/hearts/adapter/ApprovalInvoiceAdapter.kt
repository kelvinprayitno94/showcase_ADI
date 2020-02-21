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
import com.hino.hearts.network.response.approve.ApprovalListResponse

class ApprovalInvoiceAdapter(var context: Context, var data: ApprovalListResponse.ApprovalListData) :
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
        data.orders?.size?.let {
            return it
        }
        return 0
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {

        var item = data.orders?.get(position)

        item?.run {
            holder.type.text = name
            holder.inv_no.text = orderCode
            holder.warehouse.text = warehouse
            holder.qty.text = "Qty: $qty"
            holder.price.text = "Rp. $value"
        }
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val type = itemView.findViewById<TextView>(R.id.tv_type)
        val inv_no = itemView.findViewById<TextView>(R.id.tv_inv_no)
        val warehouse = itemView.findViewById<TextView>(R.id.tv_warehouse)
        val qty = itemView.findViewById<TextView>(R.id.tv_qty)
        val price = itemView.findViewById<TextView>(R.id.tv_price)
    }
}