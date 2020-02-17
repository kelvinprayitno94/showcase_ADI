package com.hino.hearts.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.hino.hearts.R


class SpinnerAdapter(context: Context, resource: Int, var item: List<String>) :
    ArrayAdapter<String>(context, resource, item) {

    override fun getCount(): Int {
        return item.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getCustomView(position, parent)
    }

    fun getCustomView(position: Int, parent: ViewGroup): View {
        val row: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.spinner, parent, false)
        val label = row.findViewById<TextView>(R.id.tv_spinner_text)
        label.text = item[position]
        return row
    }
}