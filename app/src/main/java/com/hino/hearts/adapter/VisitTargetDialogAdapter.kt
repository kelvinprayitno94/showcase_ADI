package com.hino.hearts.adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hino.hearts.R
import com.hino.hearts.model.VisitTarget
import kotlinx.android.synthetic.main.item_visit_dialog.view.*
import org.jetbrains.anko.colorAttr
import org.jetbrains.anko.textColor

/**
 * Created by Dihardja Software on 2020-02-17.
 */
class VisitTargetDialogAdapter : RecyclerView.Adapter<VisitTargetDialogAdapter.VisitTargetDialogHolder>(){

    lateinit var context: Context
    private val mData = ArrayList<VisitTarget>()

    fun setData(items: ArrayList<VisitTarget>) {
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VisitTargetDialogHolder {
        context = parent.context

        val mView = LayoutInflater.from(parent.context).inflate(R.layout.item_visit_dialog, parent, false)
        return VisitTargetDialogHolder(mView)
    }

    override fun onBindViewHolder(holder: VisitTargetDialogHolder, position: Int) {
        holder.itemView.setOnClickListener { v: View? ->
            Toast.makeText(context, "Item clicked", Toast.LENGTH_SHORT).show()
        }

        holder.bind(mData[position])
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    inner class VisitTargetDialogHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(visitTarget: VisitTarget) {
            with(itemView){
                tv_item_visit_dialog.text = visitTarget.name

                when(visitTarget.completed){
                    true-> {
                        Glide.with(context).load(context.getDrawable(R.drawable.ic_checked)).into(iv_item_visit_dialog)

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            tv_item_visit_dialog.setTextColor(context.getColor(R.color.green))
                        } else {
                            tv_item_visit_dialog.setTextColor(resources.getColor(R.color.green))
                        }
                    }
                    false -> {
                        Glide.with(context).load(context.getDrawable(R.drawable.ic_bullet)).into(iv_item_visit_dialog)

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            tv_item_visit_dialog.setTextColor(context.getColor(R.color.grey4a))
                        } else {
                            tv_item_visit_dialog.setTextColor(resources.getColor(R.color.grey4a))
                        }
                    }
                }
            }
        }
    }
}