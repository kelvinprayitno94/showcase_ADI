package com.hino.hearts.ui.dragdrop

import android.os.Bundle
import android.util.Log
import android.view.DragEvent
import android.view.View
import android.widget.HorizontalScrollView
import androidx.lifecycle.ViewModelProvider
import com.hino.hearts.R
import com.hino.hearts.adapter.DragDropListener
import com.hino.hearts.databinding.ActivityDragdropBinding
import com.hino.hearts.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_dragdrop.*

class DragDropActivity : BaseActivity<ActivityDragdropBinding>() {

    private val mViewModel: DragDropViewModel by lazy { ViewModelProvider(this).get(DragDropViewModel::class.java) }
    private val mDragDropList: MutableList<DragDropList> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBinding(R.layout.activity_dragdrop)

        initObserver()
        initViewModel()
        initEvent()
    }

    override fun initObserver() {

    }

    override fun initViewModel() {
        binding.viewModel = mViewModel

        for (header in mViewModel.headers) {
            val dragDropList = DragDropList(this, header, mViewModel.data[header]!!)
            mDragDropList.add(dragDropList)

            ll_dragdrop.addView(dragDropList)
        }
    }

    override fun initEvent() {
        v_left_area.setOnDragListener(DragDropListener(hs_dragdrop, DragDropListener.DIRECTION_LEFT))
        v_right_area.setOnDragListener(DragDropListener(hs_dragdrop, DragDropListener.DIRECTION_RIGHT))
    }

    /*class LeftDragListener(targetScrollView: HorizontalScrollView) : View.OnDragListener {
        val scrollView: HorizontalScrollView = targetScrollView

        override fun onDrag(v: View, event: DragEvent): Boolean {
            Log.d("DragDropActivity", "Left Event ${event.action}")
            when (event.action) {
                DragEvent.ACTION_DRAG_LOCATION -> {
                    Log.d("DragDropActivity", "Scroll Left")
                    scrollView.scrollBy(-20, 0)
                }
                DragEvent.ACTION_DROP -> {
                    return false
                }
            }

            return true
        }
    }

    class RightDragListener(targetScrollView: HorizontalScrollView) : DragDropListener {
        val scrollView: HorizontalScrollView = targetScrollView

        override fun onDrag(v: View, event: DragEvent): Boolean {
            Log.d("DragDropActivity", "Right Event ${event.action}")
            when (event.action) {
                DragEvent.ACTION_DRAG_LOCATION -> {
                    Log.d("DragDropActivity", "Scroll Right")
                    scrollView.scrollBy(20, 0)
                }
                DragEvent.ACTION_DROP -> {
                    return false
                }
            }

            return true
        }
    }*/
}
