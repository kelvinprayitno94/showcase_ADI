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
import com.hino.hearts.model.OpportunityModel
import org.jetbrains.anko.backgroundResource

class DragDropList(context: Context, attributeSet: AttributeSet?, defStyleRes: Int, headerText: String, background: Int, marginStart: Int, marginEnd: Int, data: MutableList<OpportunityModel.OpportunityData>) : LinearLayout(context, attributeSet, defStyleRes) {
    private val mAdapter: DragDropAdapter = DragDropAdapter()

    init {
        val inflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.layout_dragdrop_list, this, false)
        val cardView: LinearLayout = view.findViewById(R.id.ll_dragdrop_card)
        val param: LayoutParams = cardView.layoutParams as LayoutParams
        param.setMargins(marginStart, 0, marginEnd, 0)
        cardView.backgroundResource = background

        val headerTextView: TextView = view.findViewById(R.id.tv_header)
        headerTextView.text = headerText

        val recyclerView: RecyclerView = view.findViewById(R.id.rv_dragdrop)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = mAdapter
        mAdapter.list = data

        addView(view)
    }

    constructor(context: Context, headerText: String, background: Int, marginStart: Int, marginEnd: Int, data: MutableList<OpportunityModel.OpportunityData>) : this(context, null, 0, headerText, background, marginStart, marginEnd, data)
    constructor(context: Context, attributeSet: AttributeSet?, headerText: String, background: Int, marginStart: Int, marginEnd: Int, data: MutableList<OpportunityModel.OpportunityData>) : this(context, attributeSet, 0, headerText, background, marginStart, marginEnd, data)
}