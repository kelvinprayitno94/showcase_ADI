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
import java.util.*
import kotlin.collections.ArrayList

class ApprovalDocumentAdapter(
    var context: Context,
    val document: ApprovalDocModel,
    val listener: OnAdapterTap
) :
    RecyclerView.Adapter<ApprovalDocumentAdapter.Holder>() , Filterable{

    var tempDoc: ApprovalDocModel = ApprovalDocModel(ArrayList())

    init {
        tempDoc.prevSelected = document.prevSelected
        tempDoc.docList.addAll(document.docList)
    }

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
        return tempDoc.docList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val doc = tempDoc.docList[position]

        holder.accountName.text = doc.accountName

        holder.documentType.text = doc.documentType

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

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence): FilterResults {
                var constraint: CharSequence? = constraint
                val results = FilterResults()
                val filterData: MutableList<ApprovalDocumentModel> = ArrayList()
                val data = document.docList
                if (constraint == null || constraint.isEmpty() || constraint == "All") {
                    results.values = data
                    results.count = data.size
                } else if (constraint.isNotEmpty()) {
                    constraint = constraint.toString().toLowerCase()
                    for (i in data.indices) {
                        val docType = data[i].documentType.toLowerCase()
                        if (docType.contains(constraint)) {
                            filterData.add(data[i])
                        }
                    }
                    if (filterData.size != 0) {
                        results.values = filterData
                        results.count = filterData.size
                    }
                }
                return results
            }

            override fun publishResults(
                constraint: CharSequence,
                results: FilterResults
            ) {
                tempDoc.docList.clear()
                val result = results.values as? MutableList<ApprovalDocumentModel>
                result?.let {
                    tempDoc.docList.addAll(it)
                }
                notifyDataSetChanged()
            }
        }
    }
}