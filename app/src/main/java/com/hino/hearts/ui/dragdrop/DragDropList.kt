package com.hino.hearts.ui.dragdrop

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hino.hearts.R
import com.hino.hearts.adapter.DragDropAdapter

class DragDropList(context: Context, headerText: String, data: MutableList<String>) : LinearLayout(context) {
    val adapter: DragDropAdapter = DragDropAdapter()

    init {
        val inflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.layout_dragdrop_list, this, false)
        val headerTextView: TextView = view.findViewById<TextView>(R.id.tv_header)
        headerTextView.text = headerText

        val recyclerView: RecyclerView = view.findViewById<RecyclerView>(R.id.rv_dragdrop)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
        adapter.list = data

        addView(view)
    }
}