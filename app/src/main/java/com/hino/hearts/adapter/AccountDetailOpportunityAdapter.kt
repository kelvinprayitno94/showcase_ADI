package com.hino.hearts.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.hino.hearts.R
import com.hino.hearts.network.response.account.AccountListResponse

class AccountDetailOpportunityAdapter(
    var context: Context,
    var data : List<AccountListResponse.OpportunityData>?,
    val listener: OnAdapterTap
) :
    RecyclerView.Adapter<AccountDetailOpportunityAdapter.Holder>(){

    interface OnAdapterTap {
        fun onTap(pos: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            LayoutInflater.from(context).inflate(
                R.layout.adapter_account_detail_opportunity,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return data?.size!!
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        var item = data?.get(position)

        item?.let {
            holder.itemName.text = it.opportunityName
            holder.companyName.text = it.name
            holder.price.text = it.opportunityValue
        }

        holder.root.setOnClickListener {
            listener.onTap(position)
        }
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemName = itemView.findViewById<TextView>(R.id.tv_adapter_opportunity_item)
        val companyName = itemView.findViewById<TextView>(R.id.tv_adapter_opportunity_cpy_name)
        val price = itemView.findViewById<TextView>(R.id.tv_adapter_opportunity_price)
        var root = itemView.findViewById<CardView>(R.id.cl_root)
    }
}