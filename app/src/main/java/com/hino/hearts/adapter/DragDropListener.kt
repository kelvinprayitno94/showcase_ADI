package com.hino.hearts.adapter

import android.util.Log
import android.view.DragEvent
import android.view.View
import android.view.View.OnDragListener
import android.widget.FrameLayout
import android.widget.HorizontalScrollView
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.hino.hearts.R
import com.hino.hearts.ui.dragdrop.DragDropList


class DragDropListener() : OnDragListener {
    private var mScrollView: HorizontalScrollView? = null
    private var mDirection: Int = DIRECTION_LEFT

    companion object {
        const val DIRECTION_LEFT: Int = 0
        const val DIRECTION_RIGHT: Int = 1
    }

    constructor(scrollView: HorizontalScrollView, direction: Int): this() {
        this.mScrollView = scrollView
        this.mDirection = direction
    }

    override fun onDrag(v: View, event: DragEvent): Boolean {
        Log.d("DragDropActivity", "Event ${event.action}")
        when (event.action) {
            DragEvent.ACTION_DRAG_LOCATION -> {
                when (mDirection) {
                    DIRECTION_LEFT ->
                        mScrollView?.scrollBy(-20, 0)
                    DIRECTION_RIGHT ->
                        mScrollView?.scrollBy(20, 0)
                }
            }
            DragEvent.ACTION_DROP -> {
                var positionTarget: Int = -1
                if (event.localState != null) {
                    val viewSource: View = event.localState as View
                    val viewId: Int = v.id
                    val rvId: Int = R.id.rv_dragdrop

                    var target: RecyclerView? = null
                    when (viewId) {
                        rvId ->
                            target = v.rootView.findViewById(rvId)
                        else -> {
                            if (v.parent is RecyclerView) {
                                target = v.parent as RecyclerView
                                positionTarget = v.tag as Int
                            }
                            else if (mScrollView != null) {
                                Log.d("DragDropActivity", "Drop X:${event.x} Y:${event.y}")
                                val translatedX: Float = event.x + v.x + mScrollView!!.scrollX
                                val translatedY: Float = event.y + v.y + mScrollView!!.scrollY

                                //Get Drag Drop List
                                var dragDropList: DragDropList? = null
                                if (mScrollView!!.childCount == 1) {
                                    val layoutContainer: LinearLayout = mScrollView!!.getChildAt(0) as LinearLayout
                                    for (i in 0 until layoutContainer.childCount) {
                                        val child = layoutContainer.getChildAt(i)
                                        if (child.x <= translatedX && translatedX <= (child.x + child.width) &&
                                            child.y <= translatedY && translatedY <= (child.y + child.height)) {
                                            dragDropList = child as DragDropList
                                            break
                                        }
                                    }
                                }

                                if (dragDropList != null) {
                                    //Get RecyclerView
                                    val recyclerView: RecyclerView = dragDropList.findViewById(rvId)

                                    //Check if drop inside recyclerview
                                    val dragDropX: Float = translatedX - dragDropList.x
                                    val dragDropY: Float = translatedY - dragDropList.y
                                    if (recyclerView.x <= dragDropX && dragDropX <= (recyclerView.x + recyclerView.width) &&
                                        recyclerView.y <= dragDropY && dragDropY <= (recyclerView.y + recyclerView.height)) {

                                        //Set target
                                        target = recyclerView

                                        //Check if drop on item
                                        val itemX: Float = dragDropX - recyclerView.x
                                        val itemY: Float = dragDropY - recyclerView.y
                                        val itemView: View? = recyclerView.findChildViewUnder(itemX, itemY)
                                        if (itemView != null) {
                                            val rootLayout: FrameLayout? = itemView.findViewById(R.id.fl_root)
                                            positionTarget = rootLayout?.tag as Int
                                        }
                                    }
                                }
                            }
                        }
                    }

                    if (target == null)
                        return false

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
                    } else {
                        customListTarget.add(listItem)
                    }
                    adapterTarget.notifyDataSetChanged()
                }
            }
        }

        return true
    }
}