package com.hino.hearts.adapter

import android.content.ClipData
import android.os.Build
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.View.DragShadowBuilder
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hino.hearts.R
import com.hino.hearts.model.OpportunityModel
import java.text.DecimalFormat
import java.text.NumberFormat


class DragDropAdapter: RecyclerView.Adapter<DragDropAdapter.ListViewHolder>(), View.OnTouchListener {
    var list: MutableList<OpportunityModel.OpportunityData> = ArrayList()
    val formatter: NumberFormat = DecimalFormat("#,###")

    companion object {
        const val ITEM_EMPTY: Int = 0
        const val ITEM_DATA: Int = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        if (viewType == ITEM_EMPTY) {
            val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_dragdrop_empty, parent, false)
            return ListViewHolder(view)
        }

        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_dragdrop, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemViewType(position: Int): Int {
        if (list.size == 0)
            return ITEM_EMPTY

        return ITEM_DATA
    }

    override fun getItemCount(): Int {
        if (list.size == 0)
            return 1

        return list.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        if (position < list.size) {
            val item = list[position]
            val formattedNumber = "Rp${formatter.format(item.opportunityValue)}"
            holder.titleTextView?.text = item.title
            holder.accountNameTextView?.text = item.accountName
            holder.opportunityValueTextView?.text = formattedNumber

            holder.rootFrameLayout.setOnTouchListener(this)
        }

        holder.rootFrameLayout.tag = position
        holder.rootFrameLayout.setOnDragListener(DragDropListener())
    }

    override fun onTouch(v: View, event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                val data = ClipData.newPlainText("", "")
                val shadowBuilder = DragShadowBuilder(v)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    v.startDragAndDrop(data, shadowBuilder, v, 0)
                } else {
                    v.startDrag(data, shadowBuilder, v, 0)
                }
                return true
            }
            MotionEvent.ACTION_UP -> {
                v.performClick()
                return true
            }
        }
        return false
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var rootFrameLayout: FrameLayout = itemView.findViewById(R.id.fl_root)
        var titleTextView: TextView? = itemView.findViewById(R.id.tv_title)
        var accountNameTextView: TextView? = itemView.findViewById(R.id.tv_account_name)
        var opportunityValueTextView: TextView? = itemView.findViewById(R.id.tv_opportunity_value)
    }
}