package com.hino.hearts.ui.dragdrop

import android.content.Context
import android.util.AttributeSet
import android.widget.HorizontalScrollView

class DragDropScrollView(c: Context?, attrs: AttributeSet?) : HorizontalScrollView(c, attrs) {
    private var mListener: OnScrollViewListener? = null

    override fun onScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int) {
        super.onScrollChanged(l, t, oldl, oldt)

        mListener?.onScrollChanged(this)
    }

    fun setOnScrollViewListener(listener: OnScrollViewListener?) {
        mListener = listener
    }

    interface OnScrollViewListener {
        fun onScrollChanged(listener: DragDropScrollView)
    }
}