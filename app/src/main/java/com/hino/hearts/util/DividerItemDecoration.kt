package com.hino.hearts.util

import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class DividerItemDecoration(private val mDivider: Drawable) :
    RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        if (parent.getChildAdapterPosition(view) === 0) {
            return
        }
        outRect.top = mDivider.intrinsicHeight
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)

        val dividerLeft: Int = parent.getPaddingLeft()
        val dividerRight: Int = parent.getWidth() - parent.getPaddingRight()
        val childCount: Int = parent.getChildCount()
        for (i in 0 until childCount - 1) {
            val child: View = parent.getChildAt(i)
            val params: RecyclerView.LayoutParams =
                child.layoutParams as RecyclerView.LayoutParams
            val dividerTop: Int = child.bottom + params.bottomMargin
            val dividerBottom = dividerTop + mDivider.intrinsicHeight
            mDivider.setBounds(dividerLeft, dividerTop, dividerRight, dividerBottom)
            mDivider.draw(c)
        }
    }

}