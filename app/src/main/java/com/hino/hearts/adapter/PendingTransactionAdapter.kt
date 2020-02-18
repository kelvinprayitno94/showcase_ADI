package com.hino.hearts.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hino.hearts.R
import com.hino.hearts.model.PendingTransaction
import kotlinx.android.synthetic.main.item_pending_transaction.view.*

/**
 * Created by Dihardja Software on 2020-02-18.
 */
class PendingTransactionAdapter : RecyclerView.Adapter<PendingTransactionAdapter.PendingTransactionHolder>() {

    lateinit var context: Context
    private val mData = ArrayList<PendingTransaction>()

    fun setData(items: ArrayList<PendingTransaction>) {
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PendingTransactionHolder {
        context = parent.context

        val mView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_pending_transaction, parent, false)
        return PendingTransactionHolder(mView)
    }

    override fun onBindViewHolder(holder: PendingTransactionHolder, position: Int) {
        holder.bind(mData[position])
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    inner class PendingTransactionHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(pendingTransaction: PendingTransaction) {
            with(itemView) {
                tv_pending_transactions_item_title.text = pendingTransaction.title
                tv_pending_transactions_item_time.text = pendingTransaction.date
            }
        }
    }
}