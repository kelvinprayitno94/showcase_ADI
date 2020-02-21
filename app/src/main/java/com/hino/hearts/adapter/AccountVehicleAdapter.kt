package com.hino.hearts.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hino.hearts.R
import com.hino.hearts.model.AccountContactModel
import com.hino.hearts.network.response.account.AccountListResponse

class AccountVehicleAdapter(
    var context: Context,
    val document: List<AccountListResponse.VehicleData>?,
    val listener: OnAdapterTap
) :
    RecyclerView.Adapter<AccountVehicleAdapter.Holder>(){

    interface OnAdapterTap {
        fun onTap(pos: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            LayoutInflater.from(context).inflate(
                R.layout.adapter_acc_detail_contact,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return document?.size!!
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {

        val obj = document?.get(position)

        obj?.let {
            holder.accountName.text = it.name

            holder.phoneNo.text = it.code
        }

        holder.edit.setOnClickListener {
            listener.onTap(position)
        }
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val accountName = itemView.findViewById<TextView>(R.id.tv_adapter_contact_acc_name)
        val phoneNo = itemView.findViewById<TextView>(R.id.tv_adapter_contact_phone_no)
        val edit = itemView.findViewById<Button>(R.id.btn_detail_contact_edit)
    }
}