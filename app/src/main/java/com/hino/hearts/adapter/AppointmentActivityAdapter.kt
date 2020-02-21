package com.hino.hearts.adapter

import android.view.*
import android.widget.FrameLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.hino.hearts.R
import com.hino.hearts.model.AppointmentActivityModel
import com.hino.hearts.model.OpportunityModel
import java.text.DecimalFormat
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class AppointmentActivityAdapter: RecyclerView.Adapter<AppointmentActivityAdapter.ListViewHolder>() {
    var list: MutableList<AppointmentActivityModel> = ArrayList()
    var clickListener: ClickListener? = null

    private val mDateFormat: SimpleDateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_opportunity_activity, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val item = list[position]

        holder.rootFrameLayout.tag = position
        holder.titleTextView?.text = item.appointmentDate
        holder.descriptionTextView?.text = item.description

        holder.rootFrameLayout.setOnClickListener { clickListener?.onItemClicked(item) }
    }

    fun setData(items: MutableList<AppointmentActivityModel>) {
        list = items
        notifyDataSetChanged()
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var rootFrameLayout: ConstraintLayout = itemView.findViewById(R.id.fl_root)
        var titleTextView: TextView? = itemView.findViewById(R.id.tv_date)
        var descriptionTextView: TextView? = itemView.findViewById(R.id.tv_description)
    }

    interface ClickListener {
        fun onItemClicked(item: AppointmentActivityModel)
    }
}