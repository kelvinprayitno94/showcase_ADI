package com.hino.hearts.adapter

import android.view.DragEvent
import android.view.View
import android.view.View.OnDragListener
import androidx.recyclerview.widget.RecyclerView
import com.hino.hearts.R

class DragDropListener: OnDragListener {
    private var isDropped = false

    override fun onDrag(v: View, event: DragEvent): Boolean {
        when (event.action) {
            DragEvent.ACTION_DROP -> {
                isDropped = true
                var positionTarget: Int = -1
                val viewSource: View = event.localState as View
                val viewId: Int = v.id
                val rvId: Int = R.id.rv_dragdrop

                val target: RecyclerView
                when (viewId) {
                    rvId ->
                        target = v.rootView.findViewById(rvId)
                    else -> {
                        target = v.parent as RecyclerView
                        positionTarget = v.tag as Int
                    }
                }

                if (viewSource != null) {
                    val source = viewSource.parent as RecyclerView
                    val positionSource = viewSource.tag as Int

                    val adapterSource: DragDropAdapter = source.adapter as DragDropAdapter
                    val listSource: MutableList<String> = adapterSource.list
                    val listItem: String = listSource[positionSource]

                    //Remove item from source
                    listSource.removeAt(positionSource)
                    adapterSource.notifyDataSetChanged()

                    val adapterTarget: DragDropAdapter = target.adapter as DragDropAdapter
                    val customListTarget: MutableList<String> = adapterTarget.list
                    if (positionTarget >= 0) {
                        customListTarget.add(positionTarget, listItem)
                    }
                    else {
                        customListTarget.add(listItem)
                    }
                    adapterTarget.notifyDataSetChanged()
                }
            }
        }

        if (!isDropped && event.localState != null) {
            (event.localState as View).visibility = View.VISIBLE
        }
        return true
    }
}